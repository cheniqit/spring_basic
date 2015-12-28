<h3>
  酒店房价变更情况
</h3>
<div class="tb-wrapper">
    <table class="table table-hover" border="1" style="width: 95%; padding: 1px; border-collapse: collapse;<#if hideTable>display:none</#if>" >
        <thead>
        <tr id="list-tr" bgcolor="#E3F5FF">
            <th width="5%" align="center" >编号</th>
            <th width="10%" align="center" >城市</th>
            <th width="20%" align="center" >酒店名称</th>
	        <th width="10%" align="center" >房型名称</th>
            <th width="10%" align="center" >门市price</th>
            <th width="10%" align="center" >眯客price</th>
            <th width="5%" align="center" >isPromo</th>
            <th width="10%" align="center" >活动类型</th>
            <th width="10%" align="center" >活动价格</th>
            <th width="10%" align="center" >结算价格</th>
        </tr>
        </thead>
        <tbody>
        <#list list as reportBean>
        <tr>
            <td width="5%" align="center" >${reportBean_index+1}</td>
            <td width="10%" align="center" >${reportBean.cityName}</td>
            <td width="20%" align="center" >${reportBean.hotelName}</td>
            <td width="10%" align="center" >${reportBean.roomTypeName}</td>
            <td width="10%" align="center" >
                <#if reportBean.oldMarketPrice??&&reportBean.newMarketPrice??>
                    <#if reportBean.oldMarketPrice!=reportBean.newMarketPrice>
                        <font color="red">${reportBean.oldMarketPrice}->> ${reportBean.newMarketPrice}</font>
                    <#else>
                        ${reportBean.oldMarketPrice}
                    </#if>
                </#if>
            </td>
            <td width="10%" align="center" >
                <#if reportBean.oldMarketPrice??&&reportBean.newMarketPrice??>
                    <#if reportBean.oldMkPrice!=reportBean.newMkPrice>
                        <font color="red">${reportBean.oldMkPrice}->> ${reportBean.newMkPrice}</font>
                    <#else>
                    ${reportBean.oldMkPrice}
                    </#if>
                </#if>
            </td>
            <td width="5%" align="center" >
                <#if reportBean.isPromo??&&reportBean.isPromo>
                    是
                <#else>
                    否
                </#if>
            </td>
            <td width="10%" align="center" >
             <#if reportBean.promoId==1>
               今夜
             <#elseif reportBean.promoId==3>
             主题
             <#elseif reportBean.promoId==6>
             一元
             </#if>
            </td>
            <td width="10%" align="center" >
              ${reportBean.oldPromoPrice}
            </td>
            <td width="10%" align="center" >
              ${reportBean.oldSettlePrice}
            </td>
        </tr>
        </#list>
        </tbody>
    </table>
</div>