<div class="footer">
    <div class="wrapper">
        <#--<div class="slogan">-->
            <#--${indexIntroLabel} &nbsp; &nbsp;-->
                <#--这里是一个活跃的小众社区，大家相互信任，以平等 • 自由 • 奔放的价值观进行分享交流-->
                <#--<a href="https://github.com/b3log/symphony" target="_blank" class="tooltipped tooltipped-n" aria-label="${siteCodeLabel}">-->
                <#--<svg class="icon-github"><use xlink:href="#github"></use></svg></a> &nbsp;-->
            <#--<a href="https://weibo.com/p/1005056386087847/home?from=page_100505&mod=TAB&is_hot=1#place" target="_blank" class="tooltipped tooltipped-n" aria-label="${followWeiboLabel}">-->
                <#--<svg class="icon-weibo"><use xlink:href="#weibo"></use></svg></a>   &nbsp;-->
            <#--<a target="_blank" class="tooltipped tooltipped-n" aria-label="${joinQQGroupLabel}"-->
               <#--href="http://shang.qq.com/wpa/qunwpa?idkey=981d9282616274abb1752336e21b8036828f715a1c4d0628adcf208f2fd54f3a">-->
                <#--<svg class="icon-qq"><use xlink:href="#qq"></use></svg></a> &nbsp;-->
            <#--<a target="_blank" rel="noopener" class="tooltipped tooltipped-n" aria-label="${joinTelegramLabel}"-->
               <#--href="https://t.me/b3log">-->
                <#--<svg class="icon-telegram"><use xlink:href="#icon-telegram"></use></svg></a>-->
        <#--</div>-->

        <div class="fn-flex-1">
            <div class="footer-nav fn-clear">
                <a href="https://weibo.com/p/1005056386087847/home?from=page_100505&mod=TAB&is_hot=1#place" target="_blank" class="tooltipped tooltipped-n" aria-label="${followWeiboLabel}">
                    <svg class="icon-weibo"><use xlink:href="#weibo"></use></svg></a>   &nbsp;
                <a target="_blank" class="tooltipped tooltipped-n" aria-label="${joinQQGroupLabel}"
                   href="http://shang.qq.com/wpa/qunwpa?idkey=981d9282616274abb1752336e21b8036828f715a1c4d0628adcf208f2fd54f3a">
                    <svg class="icon-qq"><use xlink:href="#qq"></use></svg></a> &nbsp;

                <a rel="help" href="${servePath}/about">${aboutLabel}</a>
                <#--<a href="https://hacpai.com/article/1457158841475">API</a>-->
                <a href="${servePath}/tag/announcement">${symAnnouncementLabel}</a>
                <a href="${servePath}/domains">${domainLabel}</a>
                <a href="${servePath}/tags">${tagLabel}</a>
                <a href="${servePath}/statistic">${dataStatLabel}</a>

                <#--<div class="fn-right">&COPY; ${year}-->
                    <#--<a rel="copyright" href="https://hacpai.com" target="_blank">hacpai.com</a>-->
                    <#--${visionLabel}</div>-->
            </div>
            <div class="fn-clear ft-smaller">
                       <#--${sloganLabel}-->
                           彤迷之家 畅所欲言  大家尽情的表达对若彤的爱吧
                    <div class="fn-right">
                       Powered by <a href="https://b3log.org" target="_blank">B3log </a> •
                            <a href="https://sym.b3log.org" target="_blank">Sym</a>
                            ${version} • ${elapsed?c}ms
                    </div>
                </div>
        </div>
    </div>
</div>

<script src="${staticServePath}/js/symbol-defs${miniPostfix}.js?${staticResourceVersion}"></script>
<script src="${staticServePath}/js/lib/compress/libs.min.js?${staticResourceVersion}"></script>
<script src="${staticServePath}/js/common${miniPostfix}.js?${staticResourceVersion}"></script>
<script>
    var Label = {
        invalidPasswordLabel: "${invalidPasswordLabel}",
        loginNameErrorLabel: "${loginNameErrorLabel}",
        followLabel: "${followLabel}",
        unfollowLabel: "${unfollowLabel}",
        symphonyLabel: "${symphonyLabel}",
        visionLabel: "${visionLabel}",
        cmtLabel: "${cmtLabel}",
        collectLabel: "${collectLabel}",
        uncollectLabel: "${uncollectLabel}",
        desktopNotificationTemplateLabel: "${desktopNotificationTemplateLabel}",
        servePath: "${servePath}",
        staticServePath: "${staticServePath}",
        isLoggedIn: ${isLoggedIn?c},
        funNeedLoginLabel: '${funNeedLoginLabel}',
		notificationCommentedLabel: '${notificationCommentedLabel}',
		notificationReplyLabel: '${notificationReplyLabel}',
		notificationAtLabel: '${notificationAtLabel}',
		notificationFollowingLabel: '${notificationFollowingLabel}',
		pointLabel: '${pointLabel}',
		sameCityLabel: '${sameCityLabel}',
		systemLabel: '${systemLabel}',
		newFollowerLabel: '${newFollowerLabel}',
        makeAsReadLabel: '${makeAsReadLabel}'
        <#if isLoggedIn>,
            currentUserName: '${currentUser.userName}'
        </#if>
    };

    <#if isLoggedIn>
    Label.userKeyboardShortcutsStatus = '${currentUser.userKeyboardShortcutsStatus}';
    </#if>

    Util.init(${isLoggedIn?c});

    <#if isLoggedIn>
    // Init [User] channel
    Util.initUserChannel("${wsScheme}://${serverHost}:${serverPort}${contextPath}/user-channel");
    </#if>

    <#if mouseEffects>
    Util.mouseClickEffects();
    </#if>
</script>
<#if algoliaEnabled>
<script src="${staticServePath}/js/lib/algolia/algolia.min.js"></script>
<script>
    Util.initSearch('${algoliaAppId}', '${algoliaSearchKey}', '${algoliaIndex}');
</script>
</#if>
${footerPCCode}
