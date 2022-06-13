package com.hy.wanandroid.library.util

import android.content.Intent
import android.provider.MediaStore
import android.provider.DocumentsContract
import android.content.ContentUris
import android.content.Context
import android.widget.Toast
import android.media.MediaScannerConnection
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.widget.TextView
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.hy.wanandroid.library.BuildConfig
import java.io.File

/**
 * Created by huangyong on 2018/4/27
 * 与手机内置功能交互工具类
 */
class PhoneUtils private constructor() {
    private object Holder {
        val INSTANCE = PhoneUtils()
    }

    /**
     * 调用手机摄像头
     */
    fun openPhoneCamera(fragment: Fragment?, uri: Uri?, requestCode: Int) {
        if (fragment == null) {
            return
        }
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        //注意：这里采用了MediaStore.EXTRA_OUTPUT，也就是将拍照的图片保存到自己定义的路径中，
        //但这样的话，在onActivityResult中的intent返回参数就会为空，我们需要从自定义路径中获取图片
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
        // 图片质量，这里是高质量
        cameraIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1)
        fragment.startActivityForResult(cameraIntent, requestCode)
    }

    /**
     * 打开手机相册
     */
    fun openPhoneAlbum(fragment: Fragment?, requestCode: Int) {
        if (fragment == null) {
            return
        }
        val albumIntent = Intent(Intent.ACTION_PICK)
        albumIntent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        fragment.startActivityForResult(albumIntent, requestCode)
    }

    /**
     * 打开手机文件系统
     */
    fun openPhoneFileSystem(fragment: Fragment?, requestCode: Int) {
        if (fragment == null) {
            return
        }
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "*/*" //设置类型，这里是任意类型，任意后缀的可以这样写。
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        fragment.startActivityForResult(intent, requestCode)
    }

    /**
     * 创建手机拍照文件存储路径
     *
     * @param context  当前环境上下文参数
     * @param filePath 文件路径
     */
    fun createCameraImageFile(context: Context?, filePath: String): File {
        val path = SDCardUtils.getSDCardPath(context) + filePath
        val file = File(path, System.currentTimeMillis().toString() + ".jpg")
        FileUtils.createOrExistsFile(file)
        return file
    }

    /**
     * 获取启动相机需要的的uri
     */
    fun getCameraUri(context: Context?, file: File?): Uri {
        val uri: Uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            FileProvider.getUriForFile(
                context!!,
                BuildConfig.LIBRARY_PACKAGE_NAME + ".provider",
                file!!
            )
        } else {
            Uri.fromFile(file)
        }
        return uri
    }

    /**
     * 获取从文件系统返回的文件实际路径
     *
     * @param context The context.
     * @param uri     The uri to query.
     * @return 实际路径
     */
    fun getPath(context: Context, uri: Uri): String? {
        // DocumentProvider
        if (DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                val docId = DocumentsContract.getDocumentId(uri)
                val split = docId.split(":").toTypedArray()
                val type = split[0]
                if ("primary".equals(type, ignoreCase = true)) {
                    return Environment.getExternalStorageDirectory()
                        .toString() + File.separator + split[1]
                }
            } else if (isDownloadsDocument(uri)) {
                val id = DocumentsContract.getDocumentId(uri)
                val contentUri = ContentUris.withAppendedId(
                    Uri.parse("content://downloads/public_downloads"), java.lang.Long.valueOf(id)
                )
                return getDataColumn(context, contentUri, null, null)
            } else if (isMediaDocument(uri)) {
                val docId = DocumentsContract.getDocumentId(uri)
                val split = docId.split(":").toTypedArray()
                val type = split[0]
                var contentUri: Uri? = null
                if ("image" == type) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                } else if ("video" == type) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                } else if ("audio" == type) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                }
                val selection = "_id=?"
                val selectionArgs = arrayOf(split[1])
                return getDataColumn(context, contentUri, selection, selectionArgs)
            }
        } else if ("content".equals(uri.scheme, ignoreCase = true)) {
            return getDataColumn(context, uri, null, null)
        } else if ("file".equals(uri.scheme, ignoreCase = true)) {
            return uri.path
        }
        return null
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
    private fun getDataColumn(
        context: Context,
        uri: Uri?,
        selection: String?,
        selectionArgs: Array<String>?
    ): String? {
        var cursor: Cursor? = null
        val column = "_data"
        val projection = arrayOf(column)
        try {
            cursor =
                context.contentResolver.query(uri!!, projection, selection, selectionArgs, null)
            if (cursor != null && cursor.moveToFirst()) {
                val columnIndex = cursor.getColumnIndexOrThrow(column)
                return cursor.getString(columnIndex)
            }
        } finally {
            cursor?.close()
        }
        return null
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    private fun isExternalStorageDocument(uri: Uri): Boolean {
        return "com.android.externalstorage.documents" == uri.authority
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    private fun isDownloadsDocument(uri: Uri): Boolean {
        return "com.android.providers.downloads.documents" == uri.authority
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    private fun isMediaDocument(uri: Uri): Boolean {
        return "com.android.providers.media.documents" == uri.authority
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
    fun setReceiveAttachmentFromPhone(
        context: Context?,
        fileNameView: TextView,
        fileSuffixView: TextView,
        receiveFilePath: String
    ): String? {
        val allowTypes = arrayOf(
            Constant.TYPE_DOC, Constant.TYPE_DOCX, Constant.TYPE_JPG, Constant.TYPE_PNG,
            Constant.TYPE_PDF, Constant.TYPE_PPTX, Constant.TYPE_TXT, Constant.TYPE_XLSX
        )
        val attachmentName = receiveFilePath.substring(receiveFilePath.lastIndexOf("/") + 1)
        return if (isFileTypeValid(attachmentName, *allowTypes)) {
            val suffix = attachmentName.substring(attachmentName.lastIndexOf("."))
            fileNameView.text = attachmentName.substring(0, attachmentName.lastIndexOf("."))
            fileSuffixView.text = suffix
            attachmentName
        } else {
            Toast.makeText(context, "错误的文件类型，请重新上传", Toast.LENGTH_SHORT).show()
            fileNameView.text = ""
            fileSuffixView.text = ""
            null
        }
    }

    /**
     * 判断相机、相册或文件系统中返回的文件类型是否是需要的文件类型
     *
     * @param currentFileName 当前文件名
     * @param allowTypes      允许接收的文件类型
     * @return 判断结果
     */
    private fun isFileTypeValid(currentFileName: String?, vararg allowTypes: String): Boolean {
        if (null == currentFileName || "" == currentFileName) {
            return false
        }
        for (type in allowTypes) {
            if (currentFileName.contains(type)) {
                return true
            }
        }
        return false
    }

    companion object {
        val instance = Holder.INSTANCE

        /**
         * 通知系统图库扫描并刷新图片显示列表
         *
         * @param context   当前环境参数
         * @param imagePath 图片路径
         */
        fun notifyAlbumScanFile(context: Context?, imagePath: String) {
            MediaScannerConnection.scanFile(context, arrayOf(imagePath), null, null)
        }
    }
}