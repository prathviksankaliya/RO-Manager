package com.itcraftsolution.romanager.Utils;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

public class FileUtils {

    public static String getPathFromContentUri(Context context, Uri uri) {
        String filePath = null;

        // Check the SDK version
        if (DocumentsContract.isDocumentUri(context, uri)) {
            // For Android 4.4 and above (KITKAT)
            filePath = getPathForKitKatAndAbove(context, uri);
        } else {
            // For Android versions below 4.4
            filePath = getPathForBelowKitKat(context, uri);
        }

        return filePath;
    }

    private static String getPathForBelowKitKat(Context context, Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    private static String getPathForKitKatAndAbove(Context context, Uri uri) {
        String filePath = null;
        String wholeID = DocumentsContract.getDocumentId(uri);

        // Split the ID at colon, use second part
        String id = wholeID.split(":")[1];

        String[] projection = {MediaStore.Images.Media.DATA};
        String selection = MediaStore.Images.Media._ID + "=?";
        String[] selectionArgs = {id};

        Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, selection, selectionArgs, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        if (cursor.moveToFirst()) {
            filePath = cursor.getString(column_index);
        }
        cursor.close();

        return filePath;
    }
}
