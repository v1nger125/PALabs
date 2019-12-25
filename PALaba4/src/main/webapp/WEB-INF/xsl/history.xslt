<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" indent="yes"/>
    <xsl:template match="/historyList">
        <html>
            <body>
                <h3>History:</h3>(<a href="list">back</a>)
                <ol>
                    <xsl:for-each select="history">
                        <li>
                            <xsl:value-of select="user/id"/>|<xsl:value-of select="banDate"/>|<xsl:value-of select="duration"/>
                        </li>
                    </xsl:for-each>
                </ol>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>