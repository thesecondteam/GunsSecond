/**
 * 管理初始化
 */
var Boxtype = {
    id: "BoxtypeTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Boxtype.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '类型编码', field: 'typecode', visible: true, align: 'center', valign: 'middle'},
            {title: '类型', field: 'type', visible: true, align: 'center', valign: 'middle'},
            {title: '状态码', field: 'stateName', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Boxtype.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Boxtype.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
Boxtype.openAddBoxtype = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/boxtype/boxtype_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看详情
 */
Boxtype.openBoxtypeDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/boxtype/boxtype_update/' + Boxtype.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
Boxtype.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/boxtype/delete", function (data) {
            Feng.success("删除成功!");
            Boxtype.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("boxtypeId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 启用
 */
Boxtype.enable = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/boxtype/enable", function (data) {
            Feng.success("启用成功!");
            Boxtype.table.refresh();
        }, function (data) {
            Feng.error("启用失败!" + data.responseJSON.message + "!");
        });
        ajax.set(this.seItem);
        ajax.start();
    }
};

/**
 * 禁用
 */
Boxtype.disable = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/boxtype/disable", function (data) {
            Feng.success("禁用成功!");
            Boxtype.table.refresh();
        }, function (data) {
            Feng.error("禁用失败!" + data.responseJSON.message + "!");
        });
        ajax.set(this.seItem);
        ajax.start();
    }
};


/**
 * 查询列表
 */
Boxtype.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Boxtype.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Boxtype.initColumn();
    var table = new BSTable(Boxtype.id, "/boxtype/list", defaultColunms);
    table.setPaginationType("client");
    Boxtype.table = table.init();
});
