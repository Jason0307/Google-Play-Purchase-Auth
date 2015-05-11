

package com.augmentum.google.generate.model;

import com.augmentum.google.generate.base.BaseModel;

import com.jfinal.ext.plugin.tablebind.TableBind;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="Game.java.html"><b><i>View Source</i></b></a>
 *
 * @author Jason
 *
 */
@TableBind(tableName = "Game", pkName = "gameId")
public class Game extends BaseModel<Game> {
	private static final long serialVersionUID = 1L;
	public static Game dao = new Game();

	@Override
	public List<String> showAttrs() {
		List<String> showAttrs = new ArrayList<String>();
		showAttrs.add("gameId");
		showAttrs.add("gameName");
		showAttrs.add("createdDate");

		return showAttrs;
	}
}