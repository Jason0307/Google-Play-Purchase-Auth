package com.augmentum.google.model;

import java.io.File;
import java.io.FileInputStream;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;

@TableBind(tableName = "GoogleAccountInfo", pkName = "id")
public class GoogleAccountInfo extends Model<GoogleAccountInfo> {

    private static Logger log = Logger.getLogger(GoogleAccountInfo.class);
    private static final long serialVersionUID = 1L;

    public static GoogleAccountInfo dao = new GoogleAccountInfo();

    public GoogleAccountInfo findByGameUnique(String gameProject) {
        GoogleAccountInfo accounts = GoogleAccountInfo.dao.findFirst("SELECT * FROM GoogleAccountInfo WHERE game = ?",
                gameProject);
        return accounts;
    }

    public GoogleAccountInfo saveAccountInfo(File clientJsonFile) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = null;
        if (null != clientJsonFile) {
            try {
                rootNode = objectMapper.readTree(new FileInputStream(clientJsonFile));
                if (null != rootNode) {
                    JsonNode webNode = rootNode.get("web");
                    String clientId = webNode.get("client_id").getTextValue();
                    String clientSecret = webNode.get("client_secret").getTextValue();
                    String redirectUrl = webNode.get("redirect_uris").get(0).getTextValue();
                    String gameProject = webNode.get("game_project").getTextValue();
                    GoogleAccountInfo account = GoogleAccountInfo.dao.findByGameUnique(gameProject);
                    if (null == account) {
                        account = new GoogleAccountInfo();
                        account.set("clientId", clientId).set("clientSecret", clientSecret).set("redirectUrl", redirectUrl)
                        .set("game", gameProject).save();
                    } else {
                        account.set("clientId", clientId).set("clientSecret", clientSecret).set("redirectUrl", redirectUrl)
                        .set("game", gameProject).update();
                    }
                    return account;
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error(e.getMessage());
            }
        }

        return null;

    }
}
