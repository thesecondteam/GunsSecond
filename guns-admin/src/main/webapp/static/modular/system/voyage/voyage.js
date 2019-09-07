/**
 * voyage管理初始化
 */
var Voyage = {
    id: "VoyageTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Voyage.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '航次号', field: 'voyagenum', visible: true, align: 'center', valign: 'middle'},
            {title: '轮船号', field: 'imo', visible: true, align: 'center', valign: 'middle'},
            {title: '开始时间', field: 'starttime', visible: true, align: 'center', valign: 'middle'},
            {title: '结束时间', field: 'endtime', visible: true, align: 'center', valign: 'middle'},
            {title: '集装箱数', field: 'boxnumber', visible: true, align: 'center', valign: 'middle'},
            {title: '起点', field: 'startpointName', visible: true, align: 'center', valign: 'middle'},
            {title: '终点', field: 'endpointName', visible: true, align: 'center', valign: 'middle'},
            {title: '里程', field: 'distance', visible: true, align: 'center', valign: 'middle'},
            {title: '状态码', field: 'statename', visible: true, align: 'center', valign: 'middle'},
            {title: '装载码', field: 'loadcode', visible: true, align: 'center', valign: 'middle'},
            {title: '卸载码', field: 'unloadcode', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Voyage.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Voyage.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加voyage
 */
Voyage.openAddVoyage = function () {
    var index = layer.open({
        type: 2,
        title: '添加航次',
        area: ['1000px', '600px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/voyage/voyage_add'
    });
    this.layerIndex = index;
};

/**
* 点击生成voyage
*/
Voyage.openCreateVoyage = function () {
    var index = layer.open({
        type: 2,
        title: '生成航次',
        area: ['1000px', '600px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/voyage/voyage_create'
    });
    this.layerIndex = index;
};

/**
 * 打开查看voyage详情
 */
Voyage.openVoyageDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '航次',
            area: ['1000px', '600px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/voyage/voyage_update/' + Voyage.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 结束
 */
Voyage.finish = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/voyage/finish", function (data) {
            Feng.success("航次已结束!");
            Voyage.table.refresh();
        }, function (data) {
            Feng.error("结束异常!" + data.responseJSON.message + "!");
        });
        ajax.set(this.seItem);
        ajax.start();
    }
};
/**
 * 删除voyage
 */
Voyage.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/voyage/delete", function (data) {
            Feng.success("删除成功!");
            Voyage.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("voyageId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询voyage列表
 */
Voyage.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Voyage.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Voyage.initColumn();
    var table = new BSTable(Voyage.id, "/voyage/list", defaultColunms);
    table.setPaginationType("client");
    Voyage.table = table.init();
});

//双击添加航次详情
$('#'+Voyage.id).on("dbl-click-row.bs.table",function(e, row, $element) {
    if (Voyage.check()) {
        var index = layer.open({
            type: 2,
            title: '添加航次详情',
            area: ['1000px', '600px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/voyagedet/voyagedet_click_add?d='+row.voyagenum
        });
        this.layerIndex = index;
    }
});