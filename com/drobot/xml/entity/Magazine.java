package com.drobot.xml.entity;

public class Magazine extends AbstractPaper {

    private Chars chars;

    public Magazine() {
        paperType = PapersType.MAGAZINE;
    }

    public Magazine(String id, String date, String title, Type type,
                    boolean monthly, boolean colored, int volume,
                    boolean index, boolean gloss) {
        super(id, date, title, type, monthly, colored, volume, PapersType.MAGAZINE);
        this.chars = new Chars(colored, volume, index, gloss);
    }

    @Override
    public Chars getChars() {
        Chars result;
        if (chars != null) {
            result = new Chars(chars.isColored(), chars.getVolume(), chars.index, chars.gloss);
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
        private boolean gloss;

        public Chars() {
        }

        public Chars(boolean colored, int volume, boolean index, boolean gloss) {
            super(colored, volume);
            this.index = index;
            this.gloss = gloss;
        }

        public boolean isIndex() {
            return index;
        }

        public void setIndex(boolean index) {
            this.index = index;
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
            if (index != chars.index) {
                return false;
            }
            return gloss == chars.gloss;
        }

        @Override
        public int hashCode() {
            int result = super.hashCode();
            result = 31 * result + (index ? 1 : 0);
            result = 31 * result + (gloss ? 1 : 0);
            return result;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Chars{");
            sb.append("colored=").append(isColored());
            sb.append(", volume=").append(getVolume());
            sb.append(", index=").append(index);
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
        if (!super.equals(o)) return false;
        Magazine magazine = (Magazine) o;
        return chars != null ? chars.equals(magazine.chars) : magazine.chars == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (chars != null ? chars.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Magazine{");
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
