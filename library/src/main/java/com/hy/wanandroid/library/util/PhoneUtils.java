package com.hy.wanandroid.library.util;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.widget.TextView;
import android.widget.Toast;

import com.hy.wanandroid.library.BuildConfig;

import java.io.File;

/**
 * Created by huangyong on 2018/4/27
 * 与手机内置功能交互工具类
 */
public class PhoneUtils {
    private PhoneUtils() {
    }

    private static class Holder {
        private static final PhoneUtils PHONE_UTILS = new PhoneUtils();
    }

    public static PhoneUtils getInstance() {
        return Holder.PHONE_UTILS;
    }

    /**
     * 通知系统图库扫描并刷新图片显示列表
     *
     * @param context   当前环境参数
     * @param imagePath 图片路径
     */
    public static void notifyAlbumScanFile(Context context, String imagePath) {
        MediaScannerConnection.scanFile(context, new String[]{imagePath}, null, null);
    }

    /**
     * 调用手机摄像头
     */
    public void openPhoneCamera(Fragment fragment, Uri uri, int requestCode) {
        if (fragment == null) {
            return;
        }
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //注意：这里采用了MediaStore.EXTRA_OUTPUT，也就是将拍照的图片保存到自己定义的路径中，
        //但这样的话，在onActivityResult中的intent返回参数就会为空，我们需要从自定义路径中获取图片
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        // 图片质量，这里是高质量
        cameraIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        fragment.startActivityForResult(cameraIntent, requestCode);
    }

    /**
     * 打开手机相册
     */
    public void openPhoneAlbum(Fragment fragment, int requestCode) {
        if (fragment == null) {
            return;
        }
        Intent albumIntent = new Intent(Intent.ACTION_PICK);
        albumIntent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        fragment.startActivityForResult(albumIntent, requestCode);
    }

    /**
     * 打开手机文件系统
     */
    public void openPhoneFileSystem(Fragment fragment, int requestCode) {
        if (fragment == null) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");//设置类型，这里是任意类型，任意后缀的可以这样写。
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        fragment.startActivityForResult(intent, requestCode);
    }

    /**
     * 创建手机拍照文件存储路径
     *
     * @param context  当前环境上下文参数
     * @param filePath 文件路径
     */
    public File createCameraImageFile(Context context, String filePath) {
        String path = SDCardUtils.getSDCardPath(context) + filePath;
        File file = new File(path, System.currentTimeMillis() + ".jpg");
        FileUtils.createOrExistsFile(file);
        return file;
    }

    /**
     * 获取启动相机需要的的uri
     */
    public Uri getCameraUri(Context context, File file) {
        Uri uri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            uri = FileProvider.getUriForFile(context, BuildConfig.LIBRARY_PACKAGE_NAME + ".provider", file);
        } else {
            uri = Uri.fromFile(file);
        }
        return uri;
    }

    /**
     * 获取从文件系统返回的文件实际路径
     *
     * @param context The context.
     * @param uri     The uri to query.
     * @return 实际路径
     */
    public String getPath(final Context context, final Uri uri) {
        // DocumentProvider
        if (DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + File.separator + split[1];
                }
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {
                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{split[1]};

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    private String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    private boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    private boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    private boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * 设置从手机中接收到的附件名
     *
     * @param context         context
     * @param fileNameView    显示附件名的TextView
     * @param fileSuffixView  显示附件后缀的TextView
     * @param receiveFilePath 接收到的附件全路径
     * @return 返回附件名
     */
    public String setReceiveAttachmentFromPhone(Context context, TextView fileNameView, TextView fileSuffixView, String receiveFilePath) {
        String[] allowTypes = {Constant.TYPE_DOC, Constant.TYPE_DOCX, Constant.TYPE_JPG, Constant.TYPE_PNG,
                Constant.TYPE_PDF, Constant.TYPE_PPTX, Constant.TYPE_TXT, Constant.TYPE_XLSX};

        String attachmentName = receiveFilePath.substring(receiveFilePath.lastIndexOf("/") + 1);
        if (isFileTypeValid(attachmentName, allowTypes)) {
            String suffix = attachmentName.substring(attachmentName.lastIndexOf("."));
            fileNameView.setText(attachmentName.substring(0, attachmentName.lastIndexOf(".")));
            fileSuffixView.setText(suffix);
            return attachmentName;
        } else {
            Toast.makeText(context, "错误的文件类型，请重新上传", Toast.LENGTH_SHORT).show();
            fileNameView.setText("");
            fileSuffixView.setText("");
            return null;
        }
    }

    /**
     * 判断相机、相册或文件系统中返回的文件类型是否是需要的文件类型
     *
     * @param currentFileName 当前文件名
     * @param allowTypes      允许接收的文件类型
     * @return 判断结果
     */
    private boolean isFileTypeValid(String currentFileName, String... allowTypes) {
        if (null == currentFileName || "".equals(currentFileName)) {
            return false;
        }
        for (String type : allowTypes) {
            if (currentFileName.contains(type)) {
                return true;
            }
        }

        return false;
    }
}
