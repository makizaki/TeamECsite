<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="./css/table.css">
<link rel="stylesheet" type="text/css" href="./css/textStyle.css">
<link rel="stylesheet" type="text/css" href="./css/formStyle.css">
<title>決済確認画面</title>
<script>
	function submitAction(url) {
		$('form#form').attr('action', url);
		$('form#form').submit();
	}
</script>
</head>
<body>
	<div id="header">
		<jsp:include page="header.jsp" />
	</div>
	<div id="main">
		<div id="top">
			<p>決済確認画面</p>
		</div>

		<s:if
			test="destinationInfolist == null || destinationInfolist.size() == 0">
			<div id="message">
				<p>宛先情報がありません。</p>
			</div>
		</s:if>
		<!-- 				messegeがあるときはここから削除ボタンまで表示しない -->
		<s:else>
			<div id="message">
				<p>宛先情報を選択してください。</p>
			</div>
			<s:form id="form">
				<table class="horizon">
					<tr>

						<th>#</th>
						<th>姓</th>
						<th>名</th>
						<th>ふりがな</th>
						<th>住所</th>
						<th>電話番号</th>
						<th>メールアドレス</th>
					</tr>
					<s:iterator value="destinationInfolist" status="st">
						<tr>
							<td><s:if test="#st.index ==0">
									<input type="radio" name="id" checked="checked"
										value="<s:property  value='id' />" />

								</s:if> <s:else>
									<input type="radio" name="id"
										value="<s:property  value='id' />" />

								</s:else></td>
							<td><s:property value="familyName" /></td>
							<td><s:property value="firstName" /></td>
							<td><s:property value="familyNameKana" /><span> </span> <s:property
									value="firstNameKana" /></td>
							<td><s:property value="userAddress" /></td>
							<td><s:property value="telNumber" /></td>
							<td><s:property value="email" /></td>

						</tr>
					</s:iterator>
				</table>
				<div>
					<s:submit class="submit_btn" value="決済"
						onClick="submitAction('SettlementCompleteAction')" />
				</div>
				<div>
					<s:submit class="submit_btn" value="削除"
						onClick="submitAction('DeleteDestinationAction')" />
				</div>

			</s:form>
		</s:else>
		<s:form action="CreateDestinationAction">
			<s:submit class="submit_btn" value="新規登録" />
		</s:form>
	</div>
</body>
</html>