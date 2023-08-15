package controllers.interfaces;

import java.util.List;

import controllers.data_objects.PlayerCreationInformation;

public interface CreatorBridge {
    boolean requestCreateGame(List<PlayerCreationInformation> players);
}
