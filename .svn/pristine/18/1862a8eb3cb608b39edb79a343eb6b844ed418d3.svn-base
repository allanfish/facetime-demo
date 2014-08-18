define(function (require, exports, module) {
    tpl.messageView = require('tplurl-website/user/MessageView.tpl');

    window.MessageView = Backbone.View.extend({
        tagName: 'div',
        className: 'message-view',
        id: 'message-view',
        events: {
            "click #search_update_list": "searchUpdateList",
            // "click #update_list_opt":"showUpdateList",
            "click #file_manage_opt": "showFileMessageList",
            "click #instance_message_opt": "showInstanceMessageList",
            "click #system_message_opt": "showSystemMessageList",
            "change #accordion2 .select_message_type": "viewMessageList",
            "click .advanceSearch": "showAdvanceSearch",
            "click .searchBox .search": "doSearchMessage",
            "click .searchBox a.advance": "doSearchMessage",
            "click #fileMessageContent li":"handleFileMsg",
            "click #instanceMessageContent li":"handleChatMsg",
            "click #systemMessageContent li":"handleSystemMsg"


        },
        collectionBinder: undefined,

        initialize: function () {
            this.listenTo(collection.messageList, 'add', this.onHandleMessage);
            tplpre.messageview = Handlebars.compile(tpl.messageView);
            var elManagerFactory = new Backbone.CollectionBinder.ViewManagerFactory(this.viewUpdateCreator);
            this.collectionBinder = new Backbone.CollectionBinder(elManagerFactory);

            this.render();
        },

        render: function () {
            this.$el.html(tplpre.messageview);
            this.collectionBinder.bind(this.collection, this.$el.find('#fileMessageContent'));
            log.debug('[render] MessageView OK');

            return this;
        },
        onHandleMessage: function (message) {
            this.showMessageNum();
            /*var content = message.toJSON();
            content.messageType = "FileUploadDone";
            collection.fileMessageList.add(new MessageDTO(content));
            content.messageType = "InsFile";
            collection.instantMessageList.add(new MessageDTO(content));
            content.messageType = "UserJoin";
            collection.systemMessageList.add(new MessageDTO(content));
            this.showMessageNum();*/
        },
        viewUpdateCreator: function (model) {
            //console.log(model);
            return new MessageItemView({model: model});
        },
        searchUpdateList: function () {
            var keyword = this.$el.find("#update_list input[name='keyword']").val();
            if (!keyword) {
                this.collectionBinder.unbind();
                this.collectionBinder.bind(this.collection, this.$el.find('.updateList'));
            } else {
                collection.resultUpdateList = new MessageListDTO();
                var rs = collection.messageList.where({fromUser: keyword});
                collection.resultUpdateList.add(rs);
                this.collectionBinder.unbind();
                this.collectionBinder.bind(collection.resultUpdateList, this.$el.find('.updateList'));

            }

        },
        showUpdateList: function () {
            this.collectionBinder.unbind();
            this.collectionBinder.bind(this.collection, this.$el.find('.updateList'));
        },
        showFileMessageList: function () {
            this.collectionBinder.unbind();
            this.collectionBinder.bind(collection.fileMessageList, this.$el.find('#fileMessageContent'));
            this.$el.find('.advanceSearch').show();
            this.$el.find('.advanceBox').hide();
        },
        showInstanceMessageList: function () {
            this.collectionBinder.unbind();
            this.collectionBinder.bind(collection.instantMessageList, this.$el.find('#instanceMessageContent'));
            this.$el.find('.advanceSearch').hide();
            this.$el.find('.advanceBox').hide();

        },
        showSystemMessageList: function () {
            this.collectionBinder.unbind();
            this.collectionBinder.bind(collection.systemMessageList, this.$el.find('#systemMessageContent'));
            this.$el.find('.advanceSearch').hide();
            this.$el.find('.advanceBox').hide();

        },
        viewMessageList: function (obj) {
            var messageType = parseInt($(obj.currentTarget).val());

            if ($.inArray(messageType, [1, 2, 3, 4]) !== -1) {
                // console.log( this.$el.find('#accordion2 .accordion-group').get(messageType-1));
                $(this.$el.find('#accordion2 .accordion-group').get(messageType - 1)).find('.select_message_type').val(messageType);
                $(this.$el.find('#accordion2 .accordion-group').get(messageType - 1)).find('.accordion-heading a').first().trigger('click');
            } else {
                log.debug("not valide message type");

                return false;

            }
        },
        showAdvanceSearch: function (e) {
            if (this.$el.find('.advanceBox').css('display') == 'none') {
                this.$el.find('.advanceBox').show();
            } else {
                this.$el.find('.advanceBox').hide();
            }
        },
        doSearchMessage: function () {
            $this = this;
            var keyword = $this.$el.find("input[name='keyword']").val();
            var startTime = $this.$el.find("input[name='messageTimeStart']").val();
            var endTime = $this.$el.find("input[name='messageTimeEnd']").val();
            var checkMap = $this.$el.find("input:checked");
            var checkMapSize = checkMap.size();
            var activeTab = $this.$el.find("ul.nav li.active").attr('val');
            var contentTab = '';
            collection.tempMessageList = new MessageListDTO();
            var fileFlag = false;
            var imageFlag = false;
            var videoFlag = false;
            var audioFlag = false;
            var otherFlag = false;
            log.debug('3,');
            checkMap.each(function (i) {
                var msgType = $(this).val();
                switch (msgType) {
                    case 'file':
                        fileFlag = true;
                        break;
                    case 'image':
                        imageFlag = true;
                        break;
                    case 'video':
                        videoFlag = true;
                        break;
                    case 'audio':
                        audioFlag = true;
                        break;
                    case 'other':
                        otherFlag = true;
                        break;
                }
            });

            switch (activeTab) {
                case 'file':
                    collection.tempMessageList = collection.fileMessageList;
                    contentTab = 'fileMessageContent';
                    break;
                case 'instance':
                    collection.tempMessageList = collection.instantMessageList;
                    contentTab = 'instanceMessageContent';
                    break;
                case 'system':
                    collection.tempMessageList = collection.systemMessageList;
                    contentTab = 'systemMessageContent';
                    break;
            }

            /*log.debug(keyword+',');
             log.debug(startTime+',');
             log.debug(endTime+',');
             log.debug(checkMap);*/
            collection.resultMessageList = new MessageListDTO();
            collection.resultMessageList.reset(collection.tempMessageList.filter(function (each) {
                //var flag=true;
                var messageBody = each.get('messageBody');
                var sendDate = each.get('sendDate');
                log.debug('4,');
                if (keyword && !messageBody.match(keyword)) {
                    return false;
                } else {
                    if (startTime && endTime && checkMapSize > 0) {
                        startTime = moment(startTime).valueOf();
                        endTime = moment(endTime).valueOf();

                        if (sendDate < startTime || sendDate > endTime) {
                            return flase;
                        }

                        var flag = this.isSearchMessageType(messageBody, fileFlag, imageFlag, videoFlag, audioFlag, otherFlag);

                        if (!flag) {
                            return false;
                        }

                    } else if (endTime && checkMapSize > 0) {//startTime为空
                        endTime = moment(endTime).valueOf();

                        if (sendDate > endTime) {
                            return flase;
                        }

                        var flag = this.isSearchMessageType(messageBody, fileFlag, imageFlag, videoFlag, audioFlag, otherFlag);

                        if (!flag) {
                            return false;
                        }
                    } else if (startTime && checkMapSize > 0) {//endTime为空
                        startTime = moment(startTime).valueOf();

                        if (sendDate < endTime) {
                            return flase;
                        }

                        var flag = this.isSearchMessageType(messageBody, fileFlag, imageFlag, videoFlag, audioFlag, otherFlag);

                        if (!flag) {
                            return false;
                        }
                    } else if (startTime && endTime) {//checkMap为空
                        startTime = moment(startTime).valueOf();
                        endTime = moment(endTime).valueOf();

                        if (sendDate < startTime || sendDate > endTime) {
                            return flase;
                        }

                    } else if (startTime) {
                        startTime = moment(startTime).valueOf();

                        if (sendDate < endTime) {
                            return flase;
                        }

                    } else if (endTime) {
                        endTime = moment(endTime).valueOf();

                        if (sendDate > endTime) {
                            return flase;
                        }

                    } else if (checkMapSize > 0) {
                        log.debug('10,');
                        var flag = $this.isSearchMessageType(messageBody, fileFlag, imageFlag, videoFlag, audioFlag, otherFlag);

                        if (!flag) {
                            return false;
                        }
                    }

                }

                log.debug('222,');

                return true;
            }));


            this.collectionBinder.unbind();
            this.collectionBinder.bind(collection.resultMessageList, this.$el.find('#' + contentTab));
        },
        isSearchMessageType: function (messageBody, fileFlag, imageFlag, videoFlag, audioFlag, otherFlag) {
            log.debug('11,');
            var fileType = constants.searchFileType();
            var imageType = constants.imgType;
            var videoType = constants.mp4Type;
            var audioType = constants.mp3Type;
            var otherType = [];
            var flag = false;
            log.debug('5,');
            if (otherFlag) {//选其他
                flag = true;
                if (fileFlag) {
                    if (imageFlag && videoFlag && audioFlag) {


                    } else if (imageFlag && videoFlag) {
                        $.each(audioType, function (i, n) {
                            if (messageBody.match('.' + n)) {
                                flag = false;
                                return false;
                            }
                        });
                    } else if (imageFlag && audioFlag) {
                        $.each(videoType, function (i, n) {
                            if (messageBody.match('.' + n)) {
                                flag = false;
                                return false;
                            }
                        });
                    } else if (videoFlag && audioFlag) {
                        $.each(imageType, function (i, n) {
                            if (messageBody.match('.' + n)) {
                                flag = false;
                                return false;
                            }
                        });

                    } else if (imageFlag) {
                        $.each(videoType, function (i, n) {
                            if (messageBody.match('.' + n)) {
                                flag = false;
                                return false;
                            }
                        });
                        if (flag) {
                            $.each(audioType, function (i, n) {
                                if (messageBody.match('.' + n)) {
                                    flag = false;
                                    return false;
                                }
                            });
                        }
                    } else if (videoFlag) {
                        $.each(imageType, function (i, n) {
                            if (messageBody.match('.' + n)) {
                                flag = false;
                                return false;
                            }
                        });
                        if (flag) {
                            $.each(audioType, function (i, n) {
                                if (messageBody.match('.' + n)) {
                                    flag = false;
                                    return false;
                                }
                            });
                        }
                    } else if (audioFlag) {
                        $.each(imageType, function (i, n) {
                            if (messageBody.match('.' + n)) {
                                flag = false;
                                return false;
                            }
                        });
                        if (flag) {
                            $.each(videoType, function (i, n) {
                                if (messageBody.match('.' + n)) {
                                    flag = false;
                                    return false;
                                }
                            });
                        }


                    } else {//只选一个
                        $.each(imageType, function (i, n) {
                            if (messageBody.match('.' + n)) {
                                flag = false;
                                return false;
                            }
                        });
                        if (flag) {
                            $.each(videoType, function (i, n) {
                                if (messageBody.match('.' + n)) {
                                    flag = false;
                                    return false;
                                }
                            });
                        }
                        if (flag) {
                            $.each(audioType, function (i, n) {
                                if (messageBody.match('.' + n)) {
                                    flag = false;
                                    return false;
                                }
                            });
                        }
                    }

                } else {
                    if (imageFlag && videoFlag && audioFlag) {
                        $.each(fileType, function (i, n) {
                            if (messageBody.match('.' + n)) {
                                flag = false;
                                return false;
                            }
                        });
                    } else if (imageFlag && audioFlag) {
                        $.each(fileType, function (i, n) {
                            if (messageBody.match('.' + n)) {
                                flag = false;
                                return false;
                            }
                        });
                        if (flag) {
                            $.each(videoType, function (i, n) {
                                if (messageBody.match('.' + n)) {
                                    flag = false;
                                    return false;
                                }
                            });
                        }
                    } else if (videoFlag && audioFlag) {
                        $.each(fileType, function (i, n) {
                            if (messageBody.match('.' + n)) {
                                flag = false;
                                return false;
                            }
                        });
                        if (flag) {
                            $.each(imageType, function (i, n) {
                                if (messageBody.match('.' + n)) {
                                    flag = false;
                                    return false;
                                }
                            });
                        }

                    } else if (imageFlag) {
                        $.each(fileType, function (i, n) {
                            if (messageBody.match('.' + n)) {
                                flag = false;
                                return false;
                            }
                        });
                        if (flag) {
                            $.each(videoType, function (i, n) {
                                if (messageBody.match('.' + n)) {
                                    flag = false;
                                    return false;
                                }
                            });
                        }
                        if (flag) {
                            $.each(audioType, function (i, n) {
                                if (messageBody.match('.' + n)) {
                                    flag = false;
                                    return false;
                                }
                            });
                        }

                    } else if (videoFlag) {
                        $.each(fileType, function (i, n) {
                            if (messageBody.match('.' + n)) {
                                flag = false;
                                return false;
                            }
                        });
                        if (flag) {
                            $.each(imageType, function (i, n) {
                                if (messageBody.match('.' + n)) {
                                    flag = false;
                                    return false;
                                }
                            });
                        }
                        if (flag) {
                            $.each(audioType, function (i, n) {
                                if (messageBody.match('.' + n)) {
                                    flag = false;
                                    return false;
                                }
                            });
                        }


                    } else if (audioFlag) {
                        $.each(fileType, function (i, n) {
                            if (messageBody.match('.' + n)) {
                                flag = false;
                                return false;
                            }
                        });
                        if (flag) {
                            $.each(imageType, function (i, n) {
                                if (messageBody.match('.' + n)) {
                                    flag = false;
                                    return false;
                                }
                            });
                        }
                        if (flag) {
                            $.each(videoType, function (i, n) {
                                if (messageBody.match('.' + n)) {
                                    flag = false;
                                    return false;
                                }
                            });
                        }
                    } else {//一个都没选，只选其他
                        $.each(fileType, function (i, n) {
                            if (messageBody.match('.' + n)) {
                                flag = false;
                                return false;
                            }
                        });
                        if (flag) {
                            $.each(imageType, function (i, n) {
                                if (messageBody.match('.' + n)) {
                                    flag = false;
                                    return false;
                                }
                            });
                        }
                        if (flag) {
                            $.each(videoType, function (i, n) {
                                if (messageBody.match('.' + n)) {
                                    flag = false;
                                    return false;
                                }
                            });
                        }
                        if (flag) {
                            $.each(audioType, function (i, n) {
                                if (messageBody.match('.' + n)) {
                                    flag = false;
                                    return false;
                                }
                            });
                        }
                    }
                }

            } else {
                flag = false;
                log.debug('6,');
                if (fileFlag) {
                    log.debug('7,');
                    log.debug(fileType);
                    $.each(fileType, function (i, n) {
                        if (messageBody.match('.' + n)) {
                            log.debug(messageBody + ',.' + n + ',');
                            flag = true;
                            return false;
                        }
                    });
                } else if (imageFlag) {

                    $.each(imageType, function (i, n) {
                        if (messageBody.match('.' + n)) {
                            flag = true;
                            return false;
                        }
                    });
                } else if (videoFlag) {

                    $.each(videoType, function (i, n) {
                        if (messageBody.match('.' + n)) {
                            flag = true;
                            return false;
                        }
                    });

                } else if (audioFlag) {

                    $.each(audioType, function (i, n) {
                        if (messageBody.match('.' + n)) {
                            flag = true;
                            return false;
                        }
                    });

                }
            }
            log.debug('8,');
            return flag;
        },
        showMessageNum:function(){
            var fileMsgNum = collection.fileMessageList.length;
            var instanceMsgNum = collection.instantMessageList.length;
            var systemMsgNum = collection.systemMessageList.length;

            if(fileMsgNum<1){
                this.$el.find('#new_file_msg_num').hide();
                this.$el.find('#tabFile .nodata').show();
            }else{
                this.$el.find('#new_file_msg_num').text(fileMsgNum).show();
                this.$el.find('#tabFile .nodata').hide();

            }

            if(instanceMsgNum<1){
                this.$el.find('#new_instance_msg_num').hide();
                this.$el.find('#tabInstance .nodata').show();
            }else{
                this.$el.find('#new_instance_msg_num').text(instanceMsgNum).show();
                this.$el.find('#tabInstance .nodata').hide();

            }

            if(systemMsgNum<1){
                this.$el.find('#new_system_msg_num').hide();
                this.$el.find('#tabSystem .nodata').show();
            }else{
                this.$el.find('#new_system_msg_num').text(systemMsgNum).show();
                this.$el.find('#tabSystem .nodata').hide();
            }
        },
        handleFileMsg:function(e){


        },
        handleChatMsg:function(e){
            log.debug('a1,');
           var userId=parseInt($(e.currentTarget).find("span[name='message_content']").attr('val'));
           var msgType=$(e.currentTarget).find("span[name='message_content']").attr('data-type');
            log.debug(constants.messageType.ChatMessage);
            log.debug('a2,');

            log.debug(msgType);
           if(msgType==constants.messageType.ChatMessage){
               var chatUserNum=collection.chatUserList.size();
               var userModel = collection.userList.findWhere({userId: userId});
               var userFind=collection.chatUserList.findWhere({userId: userId});
               if(!userFind){
                   collection.chatUserList.unshift(userModel);
               }
               //$this=this;
               log.debug('a3,');
               log.debug(chatUserNum);
               var avatar="/os/assets/website/img/defaultAvatar64man.png";
               var userName=userModel.get('userName');
               var titleContent="<div class='diyTitle'><img style='width:20px;height:20px' src='"+avatar+"'/><span style='margin-left: 5px;' class='userName'>"+userName+"</span><span style='margin-left: 20px;'>视频</span></div>";
               if(chatUserNum<1){//没有打开聊天窗口，只弹出单人聊天
                   view.usualContactTreeView=new UsualContactTreeView({collection: collection.usualContactList});
                   view.usualContactTreeView.chatWindowView = new ChatWindowView({model: userModel});
                   view.usualContactTreeView.singleDialog = $.dialog({
                       id: "testID1",
                       title: titleContent,
                       content: view.usualContactTreeView.chatWindowView.el,
                       close: function () {
                           $this.singleDialog = undefined;
                           if (collection.chatUserList.size() < 2) {
                               collection.chatUserList = new UserListDTO();
                               //$this.multipleDialog = undefined;
                           }
                       }
                   });

               }else{//已打开聊天窗口
                   var newChatUserNum=collection.chatUserList.size();
                   log.debug('abcc,');
                   log.debug(newChatUserNum);
                   log.debug(collection.chatUserList);
                   if(newChatUserNum==1){
                       return false;
                   }
                   log.debug('abccd,');
                   if (view.usualContactTreeView.singleDialog != undefined){
                       log.debug('abcce,');
                       view.usualContactTreeView.singleDialog.close();
                   }
                   chatWindowsView = new ChatWindowsView({collection: collection.chatUserList});

                   if (view.usualContactTreeView.multipleDialog == undefined) {

                       view.usualContactTreeView.multipleDialog = $.dialog({
                           id: "testID2",
                           title: titleContent,
                           content: chatWindowsView.el,
                           max:false,
                           close: function () {
                               view.usualContactTreeView.multipleDialog = undefined;
                               collection.chatUserList=new UserListDTO();

                           }
                       });
                   } else {
                       view.usualContactTreeView.multipleDialog.content(chatWindowsView.el).title(titleContent);

                       var liLen= $('.chat-windows ul.nav li').first().height();
                       var liTotalNum=$('.chat-windows ul.nav li').size();
                       var liTotalLen=liLen*liTotalNum;
                       var parentHeight=$('.chat-windows div.tabbable').height();
                       $('.chat-windows ul.nav').height(liTotalLen);

                       if(liTotalLen>parentHeight){
                           $('.chat-windows div.tabbable').find('a.scrollTop').show();
                           $('.chat-windows div.tabbable').find('a.scrollBottom').show();
                       }

                   }

                   $('.chat-windows ul.nav li').first().find('a').trigger('click');
               }

               if($('td.ui_main').size()>0 && $('td.ui_main').css('display')=='none'){
                   $('a.ui_min').trigger('click');
               }
           }
        },
        handleSystemMsg:function(e){


        },
        close: function () {
            this.collectionBinder.unbind();
            this.off();
            this.undelegateEvents();
            this.remove();

            view.mainbox.hideSlideRight();
        }
    });
});
