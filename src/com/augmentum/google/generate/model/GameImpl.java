

package com.augmentum.google.generate.model;

/**
 * <a href="GameImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jason
 *
 */
public class GameImpl {
	private static GameImpl instance = new GameImpl();

	private GameImpl() {
	}

	public Game findByGameName(String gameName) {
		StringBuilder sBuilder = new StringBuilder("SELECT * FROM Game WHERE ");
		sBuilder.append("gameName = ? ");

		Game entity = Game.dao.findFirst(sBuilder.toString(), gameName);

		return entity;
	}

	public static GameImpl getDAO() {
		return instance;
	}
}