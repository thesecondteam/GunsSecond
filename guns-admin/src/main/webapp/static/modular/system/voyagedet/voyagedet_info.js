/**
 * 初始化voyagedet详情对话框
 */
var VoyagedetInfoDlg = {
    voyagedetInfoData : {},
    validateFields:{
        voyagedetid:{
            validators:{
                notEmpty:{
                    message:"航次详情编号不能为空"
                }
            }
        },
        voyagenum:{
            validators:{
                notEmpty:{
                    message:"航次号不能为空"
                }
            }
        },
        boxid:{
            validators:{
                notEmpty:{
                    message:"集装箱号不能为空"
                }
            }
        },
        boxtype:{
            validators:{
                notEmpty:{
                    message:"集装箱类型不能为空"
                }
            }
        },
        boxsize:{
            validators:{
                notEmpty:{
                    message:"集装箱尺寸不能为空"
                }
            }
        },
        boxgoods:{
            validators:{
                notEmpty:{
                    message:"集装箱所装货物不能为空"
                }
            }
        },
        createtime:{
            validators:{
                notEmpty:{
                    message:"创建时间不能为空"
                }
            }
        },
        operation:{
            validators:{
                notEmpty:{
                    message:"装/卸种类不能为空"
                }
            }
        },
        finish:{
            validators:{
                notEmpty:{
                    message:"是否完成不能为空"
                }
            }
        }
    }
};
/**
 * 验证数据是否为空
 */
VoyagedetInfoDlg.validate = function () {
    $('#voyagedetForm').data("bootstrapValidator").resetForm();
    $('#voyagedetForm').bootstrapValidator('validate');
    return $("#voyagedetForm").data('bootstrapValidator').isValid();
}
/**
 * 清除数据
 */
VoyagedetInfoDlg.clearData = function() {
    this.voyagedetInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
VoyagedetInfoDlg.set = function(key, val) {
    this.voyagedetInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
VoyagedetInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
VoyagedetInfoDlg.close = function() {
    parent.layer.close(window.parent.Voyagedet.layerIndex);
}

/**
 * 收集数据
 */
VoyagedetInfoDlg.collectData = function() {
    this
    .set('id')
    .set('voyagedetid')
    .set('voyagenum')
    .set('boxid')
    .set('boxtype')
    .set('boxsize')
    .set('boxgoods')
/*    .set('statecode')*/
    .set('createtime')
    .set('operation')
    .set('finish')
    .set('spareone')
    .set('sparetwo');
}

/**
 * 提交添加
 */
VoyagedetInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/voyagedet/add", function(data){
        Feng.success("添加成功!");
        window.parent.Voyagedet.table.refresh();
        VoyagedetInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.voyagedetInfoData);
    ajax.start();
}

/**
 * 提交生成
 */
VoyagedetInfoDlg.createSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/voyagedet/create", function(data){
        Feng.success("生成成功!");
        window.parent.Voyagedet.table.refresh();
        VoyagedetInfoDlg.close();
    },function(data){
        Feng.error("生成失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.voyagedetInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
VoyagedetInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/voyagedet/update", function(data){
        Feng.success("修改成功!");
        window.parent.Voyagedet.table.refresh();
        VoyagedetInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.voyagedetInfoData);
    ajax.start();
}
/**
 *动态增加集装箱号option
 */
VoyagedetInfoDlg.getBoxCode = function () {
    var BoxCode = $("select[id=boxid]").val();
    $("select[id=boxid]").empty();      //清空
    $("#boxid").append("<option value=''>请选择集装箱箱号</option>");
    $.ajax({url:Feng.ctxPath+'/box/getBoxCode',
        type:"post",
        data:{
            BoxCode : BoxCode
        },
        cache: false,
        processData: false,
        contentType: false,
        error:function(){
        },
        success:function(listBoxCode){
            if(listBoxCode && listBoxCode.length != 0){
                for(var i=0; i<listBoxCode.length; i++){
                    if(listBoxCode[i]!=""){
                        var option="<option value=\""+listBoxCode[i]+"\"";
                        option += ">"+listBoxCode[i]+"</option>";  //动态添加数据
                        $("select[id=boxid]").append(option);
                    }
                }
            }
        }
    });
}
/**
 *动态增加航次号option
 */
VoyagedetInfoDlg.getVoyageNum = function () {
    var VoyageNum = $("select[id=voyagenum]").val();
    $("select[id=voyagenum]").empty();      //清空
    $("#voyagenum").append("<option value=''>请选择航次号</option>");
    $.ajax({url:Feng.ctxPath+'/voyage/getVoyageNum',
        type:"post",
        data:{
            VoyageNum : VoyageNum
        },
        cache: false,
        processData: false,
        contentType: false,
        error:function(){
        },
        success:function(listVoyageNum){
            if(listVoyageNum && listVoyageNum.length != 0){
                for(var i=0; i<listVoyageNum.length; i++){
                    if(listVoyageNum[i]!=""){
                        var option="<option value=\""+listVoyageNum[i]+"\"";
                        option += ">"+listVoyageNum[i]+"</option>";  //动态添加数据
                        $("select[id=voyagenum]").append(option);
                    }
                }
            }
        }
    });
}
$(function() {
    Feng.initValidator("voyagedetForm", VoyagedetInfoDlg.validateFields);
});
