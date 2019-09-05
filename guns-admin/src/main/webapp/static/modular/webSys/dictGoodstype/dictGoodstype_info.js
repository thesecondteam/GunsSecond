
/**
 * 初始化goodstype详情对话框
 */
var DictGoodstypeInfoDlg = {
    dictGoodstypeInfoData : {},
    validateFields:{
        goodstype:{
            validators:{
                notEmpty:{
                    message:"货物类型不能为空"
                }
            }
        },
        statecode:{
            validators:{
                notEmpty:{
                    message:"状态码不能为空"
                }
            }
        },
}
};

/**
 * 清除数据
 */
DictGoodstypeInfoDlg.clearData = function() {
    this.dictGoodstypeInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
DictGoodstypeInfoDlg.set = function(key, val) {
    this.dictGoodstypeInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
DictGoodstypeInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
DictGoodstypeInfoDlg.close = function() {
    parent.layer.close(window.parent.DictGoodstype.layerIndex);
}

/**
 * 收集数据
 */
DictGoodstypeInfoDlg.collectData = function() {
    this
        .set('id')
    .set('goodstype')
    .set('statecode')
    .set('spare')
    .set('spare1');
}

/**
 * 提交添加
 */
DictGoodstypeInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
        //提交信息
        var ajax = new $ax(Feng.ctxPath + "/dictGoodstype/add", function (data) {
            Feng.success("添加成功!");
            window.parent.DictGoodstype.table.refresh();
            DictGoodstypeInfoDlg.close();
        }, function (data) {
            Feng.error("添加失败!" + data.responseJSON.message + "!");
        });
        ajax.set(this.dictGoodstypeInfoData);
        ajax.start();
    }

/**
 * 提交修改
 */
DictGoodstypeInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
        //提交信息
        var ajax = new $ax(Feng.ctxPath + "/dictGoodstype/update", function (data) {
            Feng.success("修改成功!");
            window.parent.DictGoodstype.table.refresh();
            DictGoodstypeInfoDlg.close();
        }, function (data) {
            Feng.error("修改失败!" + data.responseJSON.message + "!");
        });
        console.log(this.dictGoodstypeInfoData);
        ajax.set(this.dictGoodstypeInfoData);
        ajax.start();
    }

/**
 * 验证数据是否为空
 */
DictGoodstypeInfoDlg.validate = function () {
    $('#goodsForm').data("bootstrapValidator").resetForm();
    $('#goodsForm').bootstrapValidator('validate');
    return $("#goodsForm").data('bootstrapValidator').isValid();
}
$(function() {
Feng.initValidator("goodsForm",DictGoodstypeInfoDlg.validateFields);
});





