package je.project.domain;

import java.sql.Timestamp;

/**
 * 日志
 * @author 刘海鑫
 */
public class Log{
    private Timestamp timestamp;
    private Integer level;
    private String label;
    private String content;

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Log [content=" + content + ", label=" + label + ", level=" + level + ", timestamp=" + timestamp + "]";
    }

    
}