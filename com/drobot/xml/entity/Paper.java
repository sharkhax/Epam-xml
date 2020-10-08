package com.drobot.xml.entity;

public class Paper extends AbstractPaper {

    private Chars chars;

    public Paper() {
        paperType = PapersType.PAPER;
    }

    public Paper(String id, String date, String title, Type type,
                 boolean monthly, boolean colored, int volume, boolean index) {
        super(id, date, title, type, monthly, colored, volume, PapersType.PAPER);
        this.chars = new Chars(colored, volume, index);
    }

    @Override
    public Chars getChars() {
        Chars result;
        if (chars != null) {
            result = new Chars(chars.isColored(), chars.getVolume(), chars.index);
        } else {
            result = new Chars();
        }
        return result;
    }

    public void setChars(Chars chars) {
        this.chars = chars;
    }

     public class Chars extends AbstractPaper.Chars {

        private boolean index;

        public Chars() {
            super();
        }

        public Chars(boolean colored, int volume, boolean index) {
            super(colored, volume);
            this.index = index;
        }

        public boolean isIndex() {
            return index;
        }

        public void setIndex(boolean index) {
            this.index = index;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            if (!super.equals(o)) {
                return false;
            }
            Chars chars = (Chars) o;
            return index == chars.index;
        }

        @Override
        public int hashCode() {
            int result = super.hashCode();
            result = 31 * result + (index ? 1 : 0);
            return result;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Chars{");
            sb.append("colored=").append(isColored());
            sb.append(", volume=").append(getVolume());
            sb.append(", index=").append(index);
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
        if (!super.equals(o)) {
            return false;
        }
        Paper paper = (Paper) o;
        return chars != null ? chars.equals(paper.chars) : paper.chars == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (chars != null ? chars.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Paper{");
        sb.append("id='").append(getId()).append('\'');
        sb.append(", date='").append(getDate()).append('\'');
        sb.append(", title='").append(getTitle()).append('\'');
        sb.append(", type=").append(getType());
        sb.append(", monthly=").append(isMonthly());
        sb.append(", chars=").append(chars);
        sb.append('}');
        return sb.toString();
    }
}
