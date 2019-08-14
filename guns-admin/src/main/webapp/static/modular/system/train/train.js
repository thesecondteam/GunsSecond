/**
 * 火车管理初始化
 */
var Train = {
    id: "TrainTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Train.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '火车编号', field: 'traincode', visible: true, align: 'center', valign: 'middle'},
            {title: '车厢数', field: 'carriagenum', visible: true, align: 'center', valign: 'middle'},
            {title: '车厢长度', field: 'carriagelen', visible: true, align: 'center', valign: 'middle'},
            {title: '状态', field: 'stateName', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '', field: 'spare1', visible: false, align: 'center', valign: 'middle'},
            {title: '', field: 'spare2', visible: false, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Train.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Train.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加火车
 */
Train.openAddTrain = function () {
    var index = layer.open({
        type: 2,
        title: '添加火车',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/train/train_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看火车详情
 */
Train.openTrainDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '火车详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/train/train_update/' + Train.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除火车
 */
Train.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/train/delete", function (data) {
            Feng.success("删除成功!");
            Train.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("trainId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 禁用
 */
Train.disable = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/train/disable", function (data) {
            Feng.success("禁用成功!");
            Train.table.refresh();
        }, function (data) {
            Feng.error("禁用失败!" + data.responseJSON.message + "!");
        });
        ajax.set(this.seItem);
        ajax.start();
    }
};

/**
 * 启用
 */
Train.enable = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/train/enable", function (data) {
            Feng.success("启用成功!");
            Train.table.refresh();
        }, function (data) {
            Feng.error("启用失败!" + data.responseJSON.message + "!");
        });
        ajax.set(this.seItem);
        ajax.start();
    }
};

/**
 * 查询火车列表
 */
Train.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Train.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Train.initColumn();
    var table = new BSTable(Train.id, "/train/list", defaultColunms);
    table.setPaginationType("client");
    Train.table = table.init();
});
