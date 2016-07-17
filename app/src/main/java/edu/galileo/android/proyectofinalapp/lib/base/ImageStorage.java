package edu.galileo.android.proyectofinalapp.lib.base;

import java.io.File;

/**
 * Created by ykro.
 */
public interface ImageStorage {
    String getImageUrl(String id);
    void upload(File file, String id, ImageStorageFinishedListener listener);
}
