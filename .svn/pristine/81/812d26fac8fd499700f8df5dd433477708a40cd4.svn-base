define(function (require, exports, module) {
    return {

        // ================ 聊天消息类型 ==================================

        /**
         * chat message
         */
        ChatMessage: "Chat",
        /**
         * 新邮件到达消息
         */
        NewMailNotify: "NewMail",
        /**
         * 邮件接收完成消息
         */
        MailNotify: "MailN",
        /**
         * 邀请好友回复， 代表被邀请方接受邀请，邀请方处理
         */
        Reply: "Reply",
        /**
         * 强行下线
         */
        ForceOffline: "ForOffl",
        /**
         * 即时文件传输
         */
        InstantFile: "InsFile",
        /**
         * 私聊时共享文件
         */
        ShareFileInPrivacy: "SFP",
        /**
         * 群聊时共享文件
         */
        ShareFileInGroup: "SFG",
        /**
         * 群聊共享即时文件，文件为客户端上传
         */
        ShareInstantFile: "SIF",
        /**
         * 群聊天记录
         */
        GroupChat: "GroupChat",
        /**
         * 某聊天组成员通知组内所有成员获取最新的成员列表
         */

        InformChatGroupToGetMemberList: "ICGGM",
        /**
         * 某聊天组成员通知组内所有成员获取最新的成员列表
         */
        InformJoinChatGroup: "InfJCG",
        /**
         * 聊天组成员退出通知
         */
        InformRemoveChatGroupMember: "InfRMem",
        /**
         * 通知编辑状态
         */
        InformEditStatus: "InfEditSta",
        /**
         * 视频邀请
         */
        VideoInvite: "VidInv",
        /**
         * 视频开始
         */
        VideoStart: "VidSta",
        /**
         * 拒绝视频
         */
        VideoRefuse: "VidRef",
        /**
         * 视频结束
         */
        VideoEnd: "VidEnd",
        /**
         * 音频邀请
         */
        AudioInvite: "AudInv",
        /**
         * 音频开始
         */
        AudioStart: "AudSta",
        /**
         * 拒绝音频
         */
        AudioRefuse: "AudRef",
        /**
         * 音频结束
         */
        AudioEnd: "AudEnd",
        /**
         * 录音消息
         */
        VoiceMail: "VoMail",

        /**
         * offline file
         */

        OfflineFile: "OffFile",

        /**
         * 聊天消息类型
         * @return {array}
         */
        getChatMsgType: function () {
            return  [this.ChatMessage, this.Reply, this.InstantFile, this.ShareFileInGroup, this.ShareFileInPrivacy, this.ShareInstantFile,
                this.GroupChat, this.InformChatGroupToGetMemberList, this.InformJoinChatGroup, this.InformRemoveChatGroupMember, this.VoiceMail,
                this.OfflineFile];
        },

        // ================ 系统消息类型 ==================================

        /**
         * 用户登入
         */
        UserJoin: "UserJoin",
        /**
         * 用户改变状态，其新状态值由
         * 获取
         */
        UserStatusChange: "UserSCh",
        /**
         * 用户离开
         */
        UserLeave: "UserLea",
        /**
         * 用户信息更新
         */
        UserInfoUpdate: "UserUpd",
        /**
         * 企业用户登录记录
         */
        Login: "Login",
        /**
         * 同事更新
         */
        ColleagueNew: "ColNew",
        /**
         * 系统消息
         */
        SystemMsg: "SystemMsg",
        /**
         * 系统视频消息
         */
        SystemVideoMsg: "SystemVideoMsg",

        /**
         * 返回系统消息类型
         * @return {array}
         */
        getSystemMsgType: function () {
            return   [this.UserJoin, this.UserStatusChange, this.UserLeave, this.UserInfoUpdate, this.Login, this.ColleagueNew, this.SystemMsg,
                this.SystemVideoMsg];
        },

        // ================ 文件消息类型 ==================================

        /**
         * 文件进入记录
         */
        FileDown: "fd",
        /**
         * 文件输出记录
         */
        FileAccessRecord: "fa",
        /**
         * 共享网盘文件更新
         */
        ShareFileNew: "SFNew",
        /**
         * 文件上传完成通知
         */
        FileUploadDone: "FileUploadDone",

        /**
         * 文件转换完成通知
         */
        FileConvertDone: "FileConvertDone",

        /**
         * 返回文件消息类型
         * @return {array}
         *
         */
        getFileMsgType: function () {
            return   [this.FileDown, this.FileAccessRecord, this.ShareFileNew, this.FileUploadDone, this.FileConvertDone];
        },


        /**
         * 视频会议邀请参会
         */
        ConferenceInvite: "ConInv",
        /**
         * 视频会议开始，通知出席
         */
        ConferenceStart: "ConSta",
        /**
         * 视频会议结束
         */
        ConferenceEnd: "ConEnd",
        /**
         * 视频会议更新
         */
        ConferenceUpdate: "ConUpd",
        /**
         * 视频会议文档更新
         */
        ConferenceDocNew: "ConDocNew",

        /**
         * 客户请求服务，客服处理
         */
        CustomerRequest: "CusReq",
        /**
         * 客服响应，客户处理
         */
        ServiceResponse: "SerRes",
        /**
         * 客户退出，客服处理
         */
        CustomerLeave: "CusLea",
        /**
         * 客服退出，客户处理
         */
        ServiceLeave: "SerLea",
        /**
         * 客户连接通知，客户发出，客服处理
         */
        CustomerConnect: "CusCon",
        /**
         * 客服连接响应，客服发出，客户处理
         */
        ServiceConnectRes: "SerCRes",
        /**
         * 客服移除客户通知，客服发出，客户处理
         */
        RemoveCustomer: "ReCus",
        /**
         * 文字说明
         */
        getMsgOptDesc:function(){
            var desc=[];
            //file
            desc[this.FileDown]={title:'进入了',fClass:'ared'};
            desc[this.FileAccessRecord]={title:'输出了',fClass:'ablue'};
            desc[this.ShareFileNew]={title:'更新了',fClass:'ablack'};
            desc[this.FileUploadDone]= {title:'上传了',fClass:'agreen'};
            desc[this.FileConvertDone]={title:'转换了',fClass:'awhile'};

            return desc;
        }
    }
})