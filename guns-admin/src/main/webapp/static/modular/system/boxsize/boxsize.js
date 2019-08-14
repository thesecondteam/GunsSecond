/**
 * 管理初始化
 */
var Boxsize = {
    id: "BoxsizeTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Boxsize.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '尺寸编码', field: 'sizecode', visible: true, align: 'center', valign: 'middle'},
            {title: '尺寸类型', field: 'sizetype', visible: true, align: 'center', valign: 'middle'},
            {title: '内长', field: 'inlength', visible: true, align: 'center', valign: 'middle'},
            {title: '内宽', field: 'inwidth', visible: true, align: 'center', valign: 'middle'},
            {title: '内高', field: 'inheight', visible: true, align: 'center', valign: 'middle'},
            {title: '外长', field: 'outheight', visible: true, align: 'center', valign: 'middle'},
            {title: '外宽', field: 'outwidth', visible: true, align: 'center', valign: 'middle'},
            {title: '体积', field: 'volume', visible: true, align: 'center', valign: 'middle'},
            {title: '容量', field: 'capacity', visible: true, align: 'center', valign: 'middle'},
            {title: '状态', field: 'stateName', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Boxsize.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Boxsize.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
Boxsize.openAddBoxsize = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/boxsize/boxsize_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看详情
 */
Boxsize.openBoxsizeDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/boxsize/boxsize_update/' + Boxsize.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 启用
 */
Boxsize.enable = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/boxsize/enable", function (data) {
            Feng.success("启用成功!");
            Boxsize.table.refresh();
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
Boxsize.disable = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/boxsize/disable", function (data) {
            Feng.success("禁用成功!");
            Boxsize.table.refresh();
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
Boxsize.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Boxsize.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Boxsize.initColumn();
    var table = new BSTable(Boxsize.id, "/boxsize/list", defaultColunms);
    table.setPaginationType("client");
    Boxsize.table = table.init();
});
