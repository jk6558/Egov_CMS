<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>파일 다운로드 테스트</title>
</head>
<body>
    <h2>파일 다운로드 테스트</h2>

    <form method="get" action="/utl/fcc/FileDownload.do">
        다운로드할 파일 경로 (서버 경로):<br/>
        <input type="text" name="filePath" size="80" placeholder="예: C:/upload/sample.txt" /><br/><br/>

        다운로드 파일명 (선택):<br/>
        <input type="text" name="downloadName" size="40" placeholder="예: 다운로드파일.txt" /><br/><br/>

        <input type="submit" value="파일 다운로드" />
    </form>
</body>
</html>