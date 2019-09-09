/**
 * 初始化order详情对话框
 */
var BusiOrderInfoDlg = {
    busiOrderInfoData : {},
    validateFields:{
        ordernumber:{
            validators:{
                notEmpty:{
                    message:"订单编号不能为空"
                }
            }
        },
        trantype:{
            validators:{
                notEmpty:{
                    message:"运输类型不能为空"
                }
            }
        },
        goodstype:{
            validators:{
                notEmpty:{
                    message:"货物类型不能为空"
                }
            }
        },
        goodsname:{
            validators:{
                notEmpty:{
                    message:"货物名称不能为空"
                }
            }
        },
        goodsvolumes:{
            validators:{
                notEmpty:{
                    message:"货物体积不能为空"
                }
            }
        },
        recephone:{
            validators:{
                notEmpty:{
                    message:"收货人电话不能为空"
                }
            }
        },
        startpoint:{
            validators:{
                notEmpty:{
                    message:"发货起点不能为空"
                }
            }
        },
        receiver:{
            validators:{
                notEmpty:{
                    message:"收货方不能为空"
                }
            }
        },
        endpoint:{
            validators:{
                notEmpty:{
                    message:"终点不能为空"
                }
            }
        },
        consiphone:{
            validators:{
                notEmpty:{
                    message:"发货方电话不能为空"
                }
            }
        },
        consignor:{
            validators:{
                notEmpty:{
                    message:"发货方不能为空"
                }
            }
        },
        creationtime:{
            validators:{
                notEmpty:{
                    message:"创建时间不能为空"
                }
            }
        }
    }
};
var dataInfo;
/**
 * 清除数据
 */
BusiOrderInfoDlg.clearData = function() {
    this.busiOrderInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BusiOrderInfoDlg.set = function(key, val) {
    this.busiOrderInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}


/**
 * 判断字符串是否为空
 */
function isEmpty(obj) {
    if (typeof obj == "undefined" || obj == null || obj == "") {
        return true;
    } else {
        return false;
    }
}


/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BusiOrderInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BusiOrderInfoDlg.close = function() {
    parent.layer.close(window.parent.BusiOrder.layerIndex);
}

/**
 * 收集数据
 */
BusiOrderInfoDlg.collectData = function() {
    this
    .set('id')
    .set('ordernumber')
    .set('trantype')
    .set('goodstype')
    .set('goodsname')
    .set('goodsvolume')
    .set('startpoint')
    .set('recephone')
    .set('receiver')
    .set('endpoint')
    .set('consiphone')
    .set('consignor')
    .set('creationtime')
    .set('ordercode')
    .set('note')
    .set('spare')
    .set('spare1');
}

/**
 * 验证数据是否为空
 */
BusiOrderInfoDlg.validate = function () {
    $('#busForm').data("bootstrapValidator").resetForm();
    $('#busForm').bootstrapValidator('validate');
    return $("#busForm").data('bootstrapValidator').isValid();
}

/**
 * 提交添加
 */
BusiOrderInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }
        //提交信息
        var ajax = new $ax(Feng.ctxPath + "/busiOrder/add", function (data) {
            Feng.success("添加成功!");
            window.parent.BusiOrder.table.refresh();
            BusiOrderInfoDlg.close();
        }, function (data) {
            Feng.error("添加失败!" + data.responseJSON.message + "!");
        });
        ajax.set(this.busiOrderInfoData);
        ajax.start();
    }

/**
 * 提交修改
 */
BusiOrderInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/busiOrder/update", function(data){
        Feng.success("修改成功!");
        window.parent.BusiOrder.table.refresh();
        BusiOrderInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.busiOrderInfoData);
    ajax.start();
}
    $(function(){
        Feng.initValidator("busForm", BusiOrderInfoDlg.validateFields);
        //提交信息
        var ajax = new $ax(Feng.ctxPath + "/busiOrder/getInfoS", function(data){
            dataInfo=data;
        },function(data){



            Feng.error("失败!" + data.responseJSON.message + "!");
        });
        ajax.start();
    });

$("#trantype").change(function () {

           if($(this).val()==0){
               $("#endpoint").empty();
           for (var key in dataInfo.harbour) {
               console.log(key);
               var option = "<option value=\"" + dataInfo.harbour[key].id + "\"";
               option += ">" + dataInfo.harbour[key].harbourname + "</option>";  //动态添加数据
               $("#endpoint").append(option);
           }
           }
    if($(this).val()==1) {
        $("#endpoint").empty();
        for (var key in dataInfo.station) {
            console.log(key);
            var option = "<option value=\"" + dataInfo.station[key].id + "\"";
            option += ">" + dataInfo.station[key].name + "</option>";  //动态添加数据
            $("#endpoint").append(option);
        }

    }

});
BusiOrderInfoDlg.getfirst = function () {

    $.ajax({url:'../busiOrder/getInfoS',
        type:"post",
        cache: false,
        processData: false,
        contentType: false,
        error:function(){
        },
        success:function(){

        }
    });
}

/**
 *动态增加货物类型
 */
BusiOrderInfoDlg.getGoodsId = function () {
    var GoodsId = $("select[id=goodstype]").val();
    $("select[id=goodstype]").empty();      //清空
    $("#goodstype").append("<option value='0'>请选择类型</option>");
    $.ajax({url:'../dictGoodstype/getGoodsId',
        type:"post",
        data:{
            GoodsId: GoodsId
        },

        cache: false,
        processData: false,
        contentType: false,
        error:function(){
        },
        success:function(listGoodsId){
            if(listGoodsId&& listGoodsId.length != 0){

                for(var i=0; i<listGoodsId.length; i++){
                    console.log(listGoodsId[i]);
                    if(listGoodsId[i]){
                        var option="<option value=\""+ listGoodsId[i].id+"\"";
                        option += ">"+listGoodsId[i].goodstype+"</option>";  //动态添加数据
                        $("select[id=goodstype]").append(option);
                    }
                }
            }
        }
    });
}

