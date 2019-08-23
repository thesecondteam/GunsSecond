/**
 * 管理初始化
 */
var Areabox = {
    id: "BoxTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Areabox.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '箱子ID', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '箱子编码', field: 'boxcode', visible: true, align: 'center', valign: 'middle'},
            {title: '所在场地', field: 'areatypeName', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Areabox.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Areabox.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
Areabox.openAddBox = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/box/box_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看详情
 */
Areabox.openBoxDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/areaMove/areamove/' + Areabox.seItem.id
        });
        this.layerIndex = index;
    }
};



/**
 * 查询列表
 */
Areabox.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Areabox.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Areabox.initColumn();
    var table = new BSTable(Areabox.id, "/areaMove/list", defaultColunms);
    table.setPaginationType("client");
    Areabox.table = table.init();
});
