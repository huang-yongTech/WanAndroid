package com.hy.wanandroid.library.util;

/**
 * Created by huangyong on 2017/11/28.
 * 常量提供工具类
 */

public final class Constant {

    private Constant() {
    }

    public static final int ERROR_CODE = -1;//请求资源不存在
    public static final int SUCCESS_CODE = 0;//请求成功标识
    //已经签到或签退
    public static final int SIGN_DONE_CODE = 1;
    //旷工
    public static final int SIGN_ABSENT_CODE = 2;
    //节假日无需签到
    public static final int SIGN_VACATION_CODE = 3;

    //请求定位
    public static final int ACCESS_FINE_LOCATION_REQUEST_CODE = 0x101;

    /**
     * 调用手机相关设备标识参数
     */
    public static final int INTENT_PHONE_CAMERA = 0x10;
    public static final int INTENT_PHONE_ALBUM = 0x11;
    public static final int INTENT_PHONE_FILE_SYSTEM = 0x12;

    /**
     * Fragment与DialogFragment之间的传值标识
     */
    public static final int REQUEST_DEPT_CODE = 0x20;
    public static final int REQUEST_ROLE_CODE = 0x21;
    public static final int REQUEST_CONTACTS_GROUP_CODE = 0x22;
    public static final String TAG_DEPARTMENT = "department";
    public static final String TAG_USER_PRIV = "userPriv";
    public static final String TAG_CONTACTS_GROUP = "contacts_group";

    /**
     * sd卡文件保存路径
     */
    public static final String ATTACHMENT_PATH = "attachment";
    public static final String NET_IMG_PATH = "download";
    public static final String CAMERA_IMG_PATH = "camera";

    /**
     * 用户信息相关参数
     */
    public static final String USER_SESSION = "User";
    public static final String USER_ID = "userId";
    public static final String SERVER = "server";
    public static final String ACCOUNT = "account";
    public static final String PASSWORD = "password";
    public static final String USER_PRIV_ID = "userPrivId";
    public static final String USER_DEPT_ID = "userDeptId";
    public static final String USER_NAME = "name";
    public static final String USER_DEPT = "department";
    public static final String USER_PWD_LAST_MODIFY_TIME = "LastModifyTime";
    public static final String USER_SIGN_IN = "userSignIn";
    public static final String USER_SIGN_OUT = "userSignOut";

    /**
     * 界面跳转通用传值标识
     */
    public static final String COMMON_ID = "CommonId";
    public static final String COMMON_TITLE = "CommonTitle";
    public static final String COMMON_INFO = "CommonInfo";
    public static final String TYPE = "type";

    /**
     * 邮件操作相关标识
     */
    public static final String EMAIL_REPLY = "email_reply";
    public static final String EMAIL_REPLY_ALL = "email_reply_all";
    public static final String EMAIL_RELAY = "email_relay";

    /**
     * 传值的类型（用于托管fragment的activity根据类型加载相应的fragment）
     */
    //文件类型
    public static final String TYPE_FILE_CONTENT = "file";
    //新闻类型
    public static final String TYPE_NEWS = "news";
    //公告类型
    public static final String TYPE_NOTIFY = "notify";
    //收件箱类型
    public static final String TYPE_IN_BOX = "inbox";
    //发件箱类型
    public static final String TYPE_OUT_BOX = "outbox";
    //草稿箱类型
    public static final String TYPE_DRAFT_BOX = "draftBox";
    //邮件回复
    public static final String TYPE_REPLY = "reply";
    //邮件回复全部
    public static final String TYPE_REPLY_ALL = "reply_all";
    //邮件转发
    public static final String TYPE_RELAY = "relay";
    //内部消息详细
    public static final String TYPE_MESSAGE_DETAIL = "message";
    //日程详细
    public static final String TYPE_SCHEDULE_DETAIL = "schedule";
    //通讯录详细
    public static final String TYPE_CONTACTS_DETAIL = "type_contacts_detail";
    //通讯录删除
    public static final String TYPE_CONTACTS_DELETE = "type_contacts_delete";

    /**
     * 附件id和附件名标识
     */
    public static final String ATTACHMENT_ID = "attachmentId";
    public static final String ATTACHMENT_NAME = "attachmentName";
    //上传的附件类型
    public static final String TYPE_DOC = ".doc";
    public static final String TYPE_DOCX = ".docx";
    public static final String TYPE_JPG = ".jpg";
    public static final String TYPE_PNG = ".png";
    public static final String TYPE_PDF = ".pdf";
    public static final String TYPE_PPTX = ".pptx";
    public static final String TYPE_TXT = ".txt";
    public static final String TYPE_XLSX = ".xlsx";

    /**
     * RxBus事件tag标签
     */
    //文档模块fragment切换标签
    public static final int REPLACE_DOCUMENT_FRAGMENT = 0x26;
    //fragment向托管的activity发送事件标签
    public static final int USER_INFO = 0x30;
    public static final int USER_PWD = 0x31;
    //activity向fragment发送事件标签
    public static final int GET_INFO = 0x32;
    public static final int GET_PWD = 0x33;
    //密码修改界面输入框清零标签
    public static final int CLEAR_PWD_EDIT = 0x34;
    //Fragment切换事件标签
    public static final int REPLACE_FRAGMENT = 0x35;

    //文件下载进度事件标签
    public static final int TAG_DOWNLOADING = 0x36;
    //文件下载成功事件标签
    public static final int TAG_DOWNLOAD_FINISH = 0x37;
    //文件下载失败事件标签
    public static final int TAG_DOWNLOAD_FAILED = 0x38;
    //文件已存在事件标签
    public static final int TAG_FILE_EXISTS = 0x39;


    //公司人员选择时间标签
    public static final int TAG_SELECT_USER = 0x40;
    public static final int TAG_SELECT_ALL = 0x41;
    public static final int TAG_SELECT_CANCEL = 0x42;
    public static final int TAG_SELECT_SUBMIT = 0x43;
    public static final int TAG_RESULT_DATA = 0x44;
    public static final String TAG_SELECT_RESULT = "select_result";


    //通讯录删除事件
    public static final int REMOVE_CONTACTS = 0x49;
    //搜索框关键字搜索事件
    public static final int SEARCH_KEY = 0x50;
    //更新邮件阅读标识事件
    public static final int UPDATE_EMAIL_READ_FLAG = 0x54;
    //删除指定邮件事件
    public static final int REMOVE_EMAIL = 0x55;
    //删除指定日程事件
    public static final int REMOVE_SCHEDULE = 0x56;
    //删除指定公告事件
    public static final int REMOVE_NOTIFY = 0x57;
    //删除指定新闻事件
    public static final int REMOVE_NEWS = 0x58;
    //删除指定文件事件
    public static final int REMOVE_FILE = 0x59;

    /**
     * 邮件发送人员选择request标签
     */
    public static final int TAG_SELECT_RECEIVER = 0x60;
    public static final int TAG_SELECT_COPY = 0x61;
    public static final int TAG_SELECT_SECRET = 0x62;

    /**
     * 日程人员选择request标签
     */
    public static final int TAG_HANDLE_USER = 0x70;
    public static final int TAG_SHARE_USER = 0x71;

    /**
     * 邮件阅读标识
     */
    public static final String READ_FLAG = "read_flag";
    public static final int FLAG_READ_ALREADY = 1;
    public static final int FLAG_READ_NO = 0;
    public static final int FLAG_ALL = -1;

    /**
     * fragment工厂方法参数标识
     */
    public static final String ARG_PARAM1 = "param1";
    public static final String ARG_PARAM2 = "param2";
    public static final String ARG_PARAM3 = "param3";

    /**
     * 正则匹配字符串
     */
    //查找是否含有img标签正则
    public static final String REGEX_FOR_IMG_TAG = "<\\s*img\\s*([^>]*)\\s*>";
    //查找是否含有p标签正则
    public static final String REGEX_FOR_P_TAG = "<\\s*p\\s*([^>]*)\\s*>";

    /**
     * 用户选择界面可展开的多级列表父列表与子列表标识
     */
    public static final int TYPE_DEPT = 0x00;
    public static final int TYPE_USER = 0x01;
    public static final int TYPE_PRIV = 0x02;

    /**
     * 通讯录分组界面
     */
    public static final int TYPE_CONTACTS_TOP = 0x01;
    public static final int TYPE_CONTACTS_GROUP = 0x02;
    public static final int TYPE_CONTACTS = 0x03;

    /**
     * 新建模块传递标识
     */
    public static final String FLOW = "流程";
    public static final String DOCUMENT = "文档";
    public static final String CUSTOMER = "客户";
    public static final String NEWS = "新闻";
    public static final String NOTIFY = "公告";
    public static final String MESSAGE = "消息";
    public static final String EMAIL = "邮件";
    public static final String CONTACTS = "通讯录";
    public static final String SCHEDULE = "日程";

    /**
     * 用户访问的服务器端口
     */
    private static String serverPort;

    /**
     * 设置访问服务器端口
     */
    public static void setServerPort(String baseUrl) {
        Constant.serverPort = baseUrl;
    }

    public static String getServerPort() {
        return serverPort;
    }

    /**
     * ReactNative相关
     */
    //native RN传参即回传标识
    public static final int NATIVE_TO_RN = 0x0001;
}
