package ser516.project3.server.helper;

import java.io.IOException;

import javax.websocket.CloseReason;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.log4j.Logger;

import ser516.project3.constants.ServerConstants;
import ser516.project3.server.Components.ServerCommonData;
import ser516.project3.server.controller.ServerController;

/**
 * The Web server socket end point class for the health server application
 *
 * @author User
 */
@ServerEndpoint(value = "/healthserver", encoders = { HealthMessageEncoder.class })
public class HealthServerConnectionEndpoint {
	final static Logger logger = Logger
			.getLogger(HealthServerConnectionEndpoint.class);

	/**
	 * Method containing logic to start sending the message json based on the
	 * value of auto send flag. If the flag is false, just send the json once,
	 * else keep sending based on the interval
	 *
	 * @param session
	 *            web socket session
	 */
	@OnOpen
	public void onOpen(final Session session) throws IOException {
		/*
		try {
			logger.info(ServerConstants.CLIENT_CONNECTED
					+ session.getBasicRemote());
			ServerController.getInstance().getConsoleController()
					.getConsoleModel()
					.logMessage(ServerConstants.CLIENT_CONNECTED);
			ServerCommonData serverCommonDataObject = ServerCommonData
					.getInstance();
			while (true) {
				boolean isShouldSend = ServerController.getInstance()
						.getTopController().getTopModel().isShouldSendData();
				boolean isAutoRepeat = ServerController.getInstance()
						.getTopController().getTopModel()
						.isAutoRepeatCheckBoxChecked();
				if (isShouldSend) {
					session.getBasicRemote()
							.sendObject(serverCommonDataObject.getMessage());
					double timeElapsed = ServerCommonData.getInstance()
							.getMessage().getTimeStamp();
					double dataInterval = ServerCommonData.getInstance()
							.getMessage().getInterval();
					ServerCommonData.getInstance().getMessage()
							.setTimeStamp(timeElapsed + dataInterval);
					ServerController.getInstance().getTimerController()
							.updateTimeStamp(timeElapsed);
					if (!isAutoRepeat)
						ServerController.getInstance().getTopController()
								.getTopModel().setShouldSendData(false);
				}
				Thread.sleep((long) (serverCommonDataObject.getMessage()
						.getInterval() * 1000));
			}

		} catch (IOException | EncodeException | InterruptedException e) {
			logger.error(
					ServerConstants.ERROR_CLIENT_CONNECTION + e.getMessage());
			ServerController.getInstance().getConsoleController()
					.getConsoleModel()
					.logMessage(ServerConstants.ERROR_CLIENT_CONNECTION);
		}*/
	}

	/**
	 * Method containing logic on what to do when message from client is
	 * received
	 *
	 * @param session
	 *            web socket session
	 */
	@OnMessage
	public void onMessage(String message, Session session) {

	}

	/**
	 * Method containing logic on what to do when session is closed
	 *
	 * @param session
	 *            web socket session
	 * @param closeReason
	 *            web socket close reason
	 */
	@OnClose
	public void onClose(Session session, CloseReason closeReason) {
		logger.info(ServerConstants.ON_CLOSE + closeReason);
		try {
			session.getBasicRemote()
					.sendText(ServerConstants.CONNECTION_CLOSED);
		} catch (IOException e) {
			logger.error(ServerConstants.ERROR_SENDING_TEXT + e.getMessage());
		}
	}

	/**
	 * Method containing logic on what to do error occurs
	 *
	 * @param session
	 *            web socket session
	 * @param throwable
	 *            Throwable object
	 */
	@OnError
	public void onError(Session session, Throwable throwable) {
		logger.error(ServerConstants.ERROR_SERVER_ENDPOINT);
	}
}