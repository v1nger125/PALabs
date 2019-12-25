<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                version="1.0">
    <xsl:output method="html" indent="yes"/>
    <xsl:template match="/userList">
        <html>
            <body>
                <h3>All users:</h3>
                (<a href="history">History</a>)
                <ol>
                    <xsl:for-each select="users">
                        <li>
                            <xsl:value-of select="id"/>|<xsl:value-of select="nickname"/>|<xsl:value-of select="registerDate"/>|<xsl:value-of select="banNumber"/>|<xsl:value-of select="banChecker"/>
                            <a href="ban?id={id}">Ban</a> <a href="unBan?id={id}">Unbab</a>
                        </li>
                    </xsl:for-each>
                </ol>
                <form action="add" method="post">
                    <label for="nickname">Chose name:
                        <input type="text" id="nickname" value="" name="nickname" />
                    </label>
                    <input type="submit" value="Add"/>
                </form>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>