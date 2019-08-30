/**
 * 操作记录管理初始化
 */
var BusiRecord = {
    id: "BusiRecordTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BusiRecord.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '旧内容', field: 'oldcontent', visible: true, align: 'center', valign: 'middle'},
            {title: '新内容', field: 'newcontent', visible: true, align: 'center', valign: 'middle'},
            {title: '操作人', field: 'oprman', visible: true, align: 'center', valign: 'middle'},
            {title: '操作时间', field: 'optime', visible: true, align: 'center', valign: 'middle'},
            {title: '操作内容', field: 'optype', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
BusiRecord.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        BusiRecord.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加操作记录
 */
BusiRecord.openAddBusiRecord = function () {
    var index = layer.open({
        type: 2,
        title: '添加操作记录',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/busiRecord/busiRecord_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看操作记录详情
 */
BusiRecord.openBusiRecordDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '操作记录详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/busiRecord/busiRecord_update/' + BusiRecord.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除操作记录
 */
BusiRecord.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/busiRecord/delete", function (data) {
            Feng.success("删除成功!");
            BusiRecord.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("busiRecordId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询操作记录列表
 */
BusiRecord.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    BusiRecord.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = BusiRecord.initColumn();
    var table = new BSTable(BusiRecord.id, "/busiRecord/list", defaultColunms);
    table.setPaginationType("client");
    BusiRecord.table = table.init();
    console.log(BusiRecord);
});
//双击修改
$('#'+BusiRecord.id).on("dbl-click-row.bs.table",function(e, row, $element) {
    if (BusiRecord.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/busiRecord/busiRecord_update/' + row.id
        });
        this.layerIndex = index;
    }
});
