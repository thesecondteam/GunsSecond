/**
 * voyagedet管理初始化
 */
var Voyagedet = {
    id: "VoyagedetTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Voyagedet.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '航次详情编号', field: 'voyagedetid', visible: true, align: 'center', valign: 'middle'},
            {title: '航线编号', field: 'voyagenum', visible: true, align: 'center', valign: 'middle'},
            {title: '箱子编号', field: 'boxid', visible: true, align: 'center', valign: 'middle'},
            {title: '箱子类型', field: 'boxtype', visible: true, align: 'center', valign: 'middle'},
            {title: '箱子尺寸', field: 'boxsize', visible: true, align: 'center', valign: 'middle'},
            {title: '箱子所装货物', field: 'boxgoods', visible: true, align: 'center', valign: 'middle'},
            {title: '状态码', field: 'statecode', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createtime', visible: true, align: 'center', valign: 'middle'},
            {title: '装卸货操作', field: 'operation', visible: true, align: 'center', valign: 'middle'},
            {title: '是否完成', field: 'finish', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Voyagedet.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Voyagedet.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加voyagedet
 */
Voyagedet.openAddVoyagedet = function () {
    var index = layer.open({
        type: 2,
        title: '添加voyagedet',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/voyagedet/voyagedet_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看voyagedet详情
 */
Voyagedet.openVoyagedetDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: 'voyagedet详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/voyagedet/voyagedet_update/' + Voyagedet.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除voyagedet
 */
Voyagedet.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/voyagedet/delete", function (data) {
            Feng.success("删除成功!");
            Voyagedet.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("voyagedetId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询voyagedet列表
 */
Voyagedet.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Voyagedet.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Voyagedet.initColumn();
    var table = new BSTable(Voyagedet.id, "/voyagedet/list", defaultColunms);
    table.setPaginationType("client");
    Voyagedet.table = table.init();
});
