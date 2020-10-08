package com.drobot.xml.entity;

public class Booklet extends AbstractPaper {

    private Chars chars;

    public Booklet() {
        paperType = PapersType.BOOKLET;
    }

    public Booklet(String id, String date, String title, Type type,
                   boolean monthly, boolean colored, int volume, boolean gloss) {
        super(id, date, title, type, monthly, colored, volume, PapersType.BOOKLET);
        this.chars = new Chars(colored, volume, gloss);
    }

    @Override
    public Chars getChars() {
        Chars result;
        if (chars != null) {
            result = new Chars(chars.isColored(), chars.getVolume(), chars.gloss);
        } else {
            result = new Chars();
        }
        return result;
    }

    public void setChars(Chars chars) {
        this.chars = chars;
    }

    public class Chars extends AbstractPaper.Chars {

        private boolean gloss;

        public Chars() {
        }

        public Chars(boolean colored, int volume, boolean gloss) {
            super(colored, volume);
            this.gloss = gloss;
        }

        public boolean isGloss() {
            return gloss;
        }

        public void setGloss(boolean gloss) {
            this.gloss = gloss;
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
            return gloss == chars.gloss;
        }

        @Override
        public int hashCode() {
            int result = super.hashCode();
            result = 31 * result + (gloss ? 1 : 0);
            return result;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Chars{");
            sb.append("colored=").append(isColored());
            sb.append(", volume=").append(getVolume());
            sb.append(", gloss=").append(gloss);
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
        Booklet booklet = (Booklet) o;
        return chars != null ? chars.equals(booklet.chars) : booklet.chars == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (chars != null ? chars.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Booklet{");
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
