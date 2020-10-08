package com.drobot.xml.entity;

public abstract class AbstractPaper {

    public enum Type {
        FASHION, NORMAL, BRAND
    }

    protected PapersType paperType;
    private String id;
    private String date = "2020-10-02";
    private String title;
    private Type type;
    private boolean monthly;
    private Chars chars;

    public AbstractPaper() {
    }

    public AbstractPaper(String id, String date, String title,
                         Type type, boolean monthly, boolean colored, int volume, PapersType paperType) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.type = type;
        this.monthly = monthly;
        this.chars = new Chars(colored, volume);
        this.paperType = paperType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isMonthly() {
        return monthly;
    }

    public void setMonthly(boolean monthly) {
        this.monthly = monthly;
    }

    public Chars getChars() {
        return new Chars(chars.colored, chars.volume);
    }

    public void setChars(Chars chars) {
        this.chars = chars;
    }

    public PapersType getPaperType() {
        return paperType;
    }

    public class Chars {

        private boolean colored;
        private int volume;

        public Chars() {
        }

        public Chars(boolean colored, int volume) {
            this.colored = colored;
            this.volume = volume;
        }

        public boolean isColored() {
            return colored;
        }

        public void setColored(boolean colored) {
            this.colored = colored;
        }

        public int getVolume() {
            return volume;
        }

        public void setVolume(int volume) {
            this.volume = volume;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Chars chars = (Chars) o;
            if (colored != chars.colored) {
                return false;
            }
            return volume == chars.volume;
        }

        @Override
        public int hashCode() {
            int result = (colored ? 1 : 0);
            result = 31 * result + volume;
            return result;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Chars{");
            sb.append("colored=").append(colored);
            sb.append(", volume=").append(volume);
            sb.append('}');
            return sb.toString();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractPaper that = (AbstractPaper) o;
        if (monthly != that.monthly) {
            return false;
        }
        if (id != null ? !id.equals(that.id) : that.id != null) {
            return false;
        }
        if (date != null ? !date.equals(that.date) : that.date != null) {
            return false;
        }
        if (title != null ? !title.equals(that.title) : that.title != null) {
            return false;
        }
        if (type != that.type) {
            return false;
        }
        return chars != null ? chars.equals(that.chars) : that.chars == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (monthly ? 1 : 0);
        result = 31 * result + (chars != null ? chars.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AbstractPaper{");
        sb.append("id='").append(id).append('\'');
        sb.append(", date='").append(date).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", type=").append(type);
        sb.append(", monthly=").append(monthly);
        sb.append(", chars=").append(chars);
        sb.append('}');
        return sb.toString();
    }
}
