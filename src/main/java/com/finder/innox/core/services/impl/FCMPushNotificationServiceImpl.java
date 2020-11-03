package com.finder.innox.core.services.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.finder.innox.core.services.FCMPushNotificationService;
import com.finder.innox.utils.CommonConstant;
import com.finder.innox.utils.NotificationDTO;
import com.google.gson.Gson;

@Service
public class FCMPushNotificationServiceImpl implements FCMPushNotificationService {

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Override
	public void pushFCMNotification(List<NotificationDTO> notificationList) throws Exception {
		logger.info("pushFCMNotification() => start");

		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					String authKey = CommonConstant.SERVER_KEY;
					String FMCurl = CommonConstant.FCM_URL;

					for (NotificationDTO notification : notificationList) {
						URL url = new URL(FMCurl);
						HttpURLConnection conn = (HttpURLConnection) url.openConnection();

						conn.setUseCaches(false);
						conn.setDoInput(true);
						conn.setDoOutput(true);

						conn.setRequestMethod("POST");
						conn.setRequestProperty("Authorization", "key=" + authKey);
						conn.setRequestProperty("Content-Type", "application/json");

						Gson gson = new Gson();

						OutputStream wr = conn.getOutputStream();
						byte[] outputBytes = gson.toJson(notification).getBytes("UTF-8");
						wr.write(outputBytes);
						wr.flush();
						conn.getInputStream();
						System.out.println("Json " + gson.toJson(notification));
						// for only output showing
						int responseCode = conn.getResponseCode();
						System.out.println("Response Code : " + responseCode);

						BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
						String inputLine;
						StringBuffer response = new StringBuffer();

						while ((inputLine = in.readLine()) != null) {
							response.append(inputLine);
						}
						in.close();
						logger.info("Response Code : " + responseCode + "\n" + "Finish " + response.toString());
						System.out.println("Finish " + response.toString());
					}

				} catch (Exception e) {
					e.printStackTrace();
					logger.error("Exception occurs while sending notification list >>> " + e.getMessage());
				}
			}
		});

		logger.info("pushFCMNotification() => finish");
		thread.start();
	}

	@Override
	public void pushFCMNOtification(NotificationDTO notification) throws Exception {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					String authKey = CommonConstant.SERVER_KEY;
					String FMCurl = CommonConstant.FCM_URL;

					URL url = new URL(FMCurl);
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();

					conn.setUseCaches(false);
					conn.setDoInput(true);
					conn.setDoOutput(true);

					conn.setRequestMethod("POST");
					conn.setRequestProperty("Authorization", "key=" + authKey);
					conn.setRequestProperty("Content-Type", "application/json");

					Gson gson = new Gson();

					OutputStream wr = conn.getOutputStream();
					byte[] outputBytes = gson.toJson(notification).getBytes("UTF-8");
					wr.write(outputBytes);
					wr.flush();
					conn.getInputStream();
					System.out.println("Json " + gson.toJson(notification));
					// for only output showing
					int responseCode = conn.getResponseCode();
					System.out.println("Response Code : " + responseCode);

					BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
					String inputLine;
					StringBuffer response = new StringBuffer();

					while ((inputLine = in.readLine()) != null) {
						response.append(inputLine);
					}
					in.close();
					logger.info("Response Code : " + responseCode + "\n" + "Finish " + response.toString());
					System.out.println("Finish " + response.toString());
				} catch (Exception e) {
					System.out.println(e.toString());
				}
			}
		});
		thread.start();

	}

}
