package com.finder.innox.core.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finder.innox.core.domain.Role;
import com.finder.innox.core.domain.State;
import com.finder.innox.core.domain.Township;
import com.finder.innox.core.domain.User;
import com.finder.innox.core.domain.UserAddress;
import com.finder.innox.core.dto.UserDTO;
import com.finder.innox.core.services.UserService;
import com.finder.innox.repository.UserAddressDao;
import com.finder.innox.repository.UserDao;
import com.finder.innox.request.UserRegisterRequest;
import com.finder.innox.utils.CommonConstant;
import com.finder.innox.utils.CommonStatus;
import com.finder.innox.utils.CommonUtil;
import com.finder.innox.utils.ImagesUtil;
import com.finder.innox.utils.UserRoleEnum;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserAddressDao addressDao;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public UserServiceImpl() {
		super();
	}

	@Override
	public List<User> getAllUser() {
		return userDao.getAllUser();
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByUserName(username, 0);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));

	}

	@Override
	public User findByEmail(String email) {
		return userDao.findByEmail(email);
	}

	@Override
	public void save(UserDTO dto) {
		User user = new User();
		user.setUserName(dto.getUserName());
		user.setEmail(dto.getEmail());
		user.setPassword(passwordEncoder.encode(dto.getPassword()));
		user.setUserRoleLevel(UserRoleEnum.ROLE_ADMIN.getCode());
		user.setRecordRegDate(new Date());
		user.setRoles(Arrays.asList(new Role(UserRoleEnum.ROLE_ADMIN.getDesc())));

		userDao.save(user);

	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	@Override
	public List<UserDTO> getDesignerList() {
		List<User> entityList = userDao.getDesignerList();
		if (entityList == null || entityList.isEmpty()) {
			return new ArrayList<UserDTO>();
		}

		List<UserDTO> dtoList = new ArrayList<UserDTO>();
		entityList.forEach(user -> {
			UserDTO userDto = new UserDTO(user);
			dtoList.add(userDto);
		});
		return dtoList;
	}

	@Override
	public UserDTO findByName(String userName, int userRole) {
		User user = userDao.findByUserName(userName, userRole);
		if (user == null) {
			throw new UsernameNotFoundException(userName);
		}
		return new UserDTO(user);
	}

	@Override
	public boolean isUserNameAlreadExist(String userName, int userRole, long userId) {
		User user = userDao.isUserNameAlreadyExist(userName, userRole, userId);
		if (user != null) {
			return true;
		}
		return false;
	}

	@Override
	public UserDTO findByPhoneNo(String phoneNo) {
		logger.info("findByPhoneNo() >> " + phoneNo);
		User user = userDao.findByPhoneNo(phoneNo);
		if (user != null) {
			return new UserDTO(user);
		}
		return null;
	}

	@Override
	public UserDTO userRegister(UserRegisterRequest registerRequest) throws Exception {
		logger.info("userRegister() >> Start");

		User user = new User();
		user.setUserName(registerRequest.getName());
		user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
		user.setEmail(registerRequest.getEmail());
		user.setPhoneNo(registerRequest.getPhoneNo());
		user.setStatus(CommonStatus.ACTIVE.getCode());
		user.setRecordRegDate(new Date());

		// user role
		if (registerRequest.getUser_role() == UserRoleEnum.ROLE_USER.getCode()) {
			user.setUserRoleLevel(UserRoleEnum.ROLE_USER.getCode());
			user.setRoles(Arrays.asList(new Role(UserRoleEnum.ROLE_USER.getDesc())));

		} else if (registerRequest.getUser_role() == UserRoleEnum.ROLE_DESIGNER.getCode()) {
			user.setUserRoleLevel(UserRoleEnum.ROLE_DESIGNER.getCode());
			user.setRoles(Arrays.asList(new Role(UserRoleEnum.ROLE_DESIGNER.getDesc())));
		}

		userDao.save(user);

		// user profile image
		if (!CommonUtil.isEmpty(registerRequest.getProfile_image())) {
			try {
				String imageName = ImagesUtil.uploadBase64File(registerRequest.getProfile_image(),
						CommonConstant.USER_DIRECTORY + CommonConstant.USER_IMAGE_DIRECTORY + user.getUserSeq() + "/",
						CommonConstant.USER_IMAGE_PREFIX, user.getUserSeq());
				user.setAvatar(imageName);

				userDao.saveOrUpdate(user);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// user address
		if ((registerRequest.getState_id() != null && registerRequest.getState_id() > 0)
				|| (registerRequest.getTownship_id() != null && registerRequest.getTownship_id() > 0)
				|| !CommonUtil.isEmpty(registerRequest.getDetail_address())) {
			UserAddress userAddress = new UserAddress();

			userAddress.setUser(user);

			if (registerRequest.getState_id() != null) {
				State state = new State();
				state.setSeq(registerRequest.getState_id());
				userAddress.setState(state);
			}

			if (registerRequest.getTownship_id() != null) {
				Township township = new Township();
				township.setSeq(registerRequest.getTownship_id());
				userAddress.setTownship(township);
			}

			userAddress.setDetailAddress(registerRequest.getDetail_address());
			userAddress.setCreatedTime(new Date());
			userAddress.setUpdatedTime(new Date());

			addressDao.save(userAddress);
		}

		logger.info("userRegister() >> End");
		return new UserDTO(userDao.get(user.getUserSeq()));
	}

	@Override
	public UserDTO getUserById(long userId) {
		logger.info("getUserById() >> User Id : " + userId);
		User user = userDao.get(userId);
		if (user != null) {
			return new UserDTO(user);
		}
		return null;
	}

	@Override
	public UserDTO userProfileUpdate(UserRegisterRequest updateRequest) throws Exception {
		logger.info("userProfileUpdate() >> Start");

		User user = userDao.get(updateRequest.getUser_id());
		if (user != null) {
			user.setUserName(updateRequest.getName());
//			user.setPassword(passwordEncoder.encode(registerRequest.getPassword())); 
			user.setEmail(updateRequest.getEmail());
			if (!CommonUtil.isEmpty(updateRequest.getPhoneNo())) {
				user.setPhoneNo(updateRequest.getPhoneNo());
			}

//			user.setStatus(CommonStatus.ACTIVE.getCode());
//			user.setRecordRegDate(new Date());
			user.setRecordUpdDate(new Date());

			// user profile image
			if (!CommonUtil.isEmpty(updateRequest.getProfile_image())) {
				try {
					String imageName = ImagesUtil.uploadBase64File(
							updateRequest.getProfile_image(), CommonConstant.USER_DIRECTORY
									+ CommonConstant.USER_IMAGE_DIRECTORY + user.getUserSeq() + "/",
							CommonConstant.USER_IMAGE_PREFIX, user.getUserSeq());
					user.setAvatar(imageName);

					userDao.saveOrUpdate(user);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			// user address
			UserAddress userAddress = addressDao.getUserAddressByUserId(user.getUserSeq());
			if (userAddress != null) {
				State state = new State();
				state.setSeq(updateRequest.getState_id());
				userAddress.setState(state);

				Township township = new Township();
				township.setSeq(updateRequest.getTownship_id());
				userAddress.setTownship(township);

				userAddress.setDetailAddress(updateRequest.getDetail_address());
				userAddress.setUpdatedTime(new Date());

				addressDao.saveOrUpdate(userAddress);
			} else {
				userAddress = new UserAddress();

				userAddress.setUser(user);

				if (updateRequest.getState_id() != null) {
					State state = new State();
					state.setSeq(updateRequest.getState_id());
					userAddress.setState(state);
				}

				if (updateRequest.getTownship_id() != null) {
					Township township = new Township();
					township.setSeq(updateRequest.getTownship_id());
					userAddress.setTownship(township);
				}

				userAddress.setDetailAddress(updateRequest.getDetail_address());
				userAddress.setCreatedTime(new Date());
				userAddress.setUpdatedTime(new Date());

				addressDao.save(userAddress);
			}

			return new UserDTO(userDao.get(user.getUserSeq()));
		}

		logger.info("userProfileUpdate() >> End");
		return null;
	}
}
