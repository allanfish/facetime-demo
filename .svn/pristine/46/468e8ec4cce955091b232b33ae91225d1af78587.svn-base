define(function (require, exports, module) {

    return {
        amqTopic: "topic://conlect.oatOS.",

        // file type constants
        docType: ['doc', 'docx', 'wps'],
        excelType: ['xls', 'xlsx', 'xlt', 'csv'],
        imgType: ['png', 'jpg', 'gif', 'jpeg', 'bmp', 'ico', 'wbmp'],
        pptType: ['ppt', 'pptx'],
        pdfType: ['pdf'],
        txtType: ['txt', 'java', 'c', 'h', 'js', 'css', 'php', 'html', 'oatw', 'oats', 'sql', 'xml', 'log'],
        mp4Type: ['mp4', 'mov', 'flv', 'webm', 'm4v', 'avi', 'ogv', 'f4v'],
        mp3Type: ['mp3', 'aac', 'm4a', 'f4a', 'ogg', 'oga'],
        zipType: ['zip', '7z', 'war', 'tar', 'rar'],
        searchFileType: function(){
            var fileMap=[];
            $.merge(fileMap,this.docType);
            $.merge(fileMap,this.excelType);
            $.merge(fileMap,this.pptType);
            $.merge(fileMap,this.pdfType);
            $.merge(fileMap,this.txtType);

            return fileMap;
        },


        // file operation
        operation: {
            'NewFolder': 'NewFolder',
            'RenameFolder': 'RenameFolder',
            'Delete': 'Delete',
            'DeletePermanently': 'DeletePermanently',
            'RenameFile': 'RenameFile',
            'UploadFile': 'UploadFile',
            'EditFile': 'EditFile',
            'ShareFile': 'ShareFile',
            'Move': 'Move',
            'Lock': 'Lock',
            'SetFolderSize': 'SetFolderSize',
            'RestoreFromRecycle': 'RestoreFromRecycle',
            'Update': 'Update',
            'RestoreVersion': 'RestoreVersion'
        },

        // file type
        fileType: {
            shareDisk: "sharedisk",
            onlineDisk: "onlinedisk",
            icon: 'icon',
            temp: 'tempfile'
        },

        // 成员的会议状态
        ConferenceMemberStatus: {
            Invited: "Invited",			//会议邀请中
            Accepted: "Accepted",		//接受会议邀请
            Refused: 'Refused',			//拒绝会议邀请
            Attended: 'Attended'		//出席会议
        },

        ConferenceType: {
            appointment: 0,             //预约会议
            immediate: 1                //即时会议
        },

        ConferenceStatus: {
            Created: "new",             //未召开会议
            Held: "held",               //会议正在召开
            Ended: "ended"              //会议召开结束
        }
    };
});
