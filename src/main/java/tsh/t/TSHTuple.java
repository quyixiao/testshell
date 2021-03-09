package tsh.t;



import java.io.Serializable;

public class TSHTuple implements Serializable {

    private TTuple11 data;


    public TSHTuple() {
        this.data = new TTuple11(null, null, null, null, null, null, null, null, null, null, null);
    }


    public TSHTuple(Object a) {
        this.data = new TTuple11(a, null, null, null, null, null, null, null, null, null, null);
    }


    public TSHTuple(Object a, Object b) {
        this.data = new TTuple11(a, b, null, null, null, null, null, null, null, null, null);
    }

    public TSHTuple(Object a, Object b, Object c) {
        this.data = new TTuple11(a, b, c, null, null, null, null, null, null, null, null);
    }


    public TSHTuple(Object a, Object b, Object c, Object d) {
        this.data = new TTuple11(a, b, c, d, null, null, null, null, null, null, null);
    }

    public TSHTuple(Object a, Object b, Object c, Object d, Object e) {
        this.data = new TTuple11(a, b, c, d, e, null, null, null, null, null, null);
    }

    public TSHTuple(Object a, Object b, Object c, Object d, Object e, Object f) {
        this.data = new TTuple11(a, b, c, d, e, f, null, null, null, null, null);
    }

    public TSHTuple(Object a, Object b, Object c, Object d, Object e, Object f, Object g) {
        this.data = new TTuple11(a, b, c, d, e, f, g, null, null, null, null);
    }


    public TSHTuple(Object a, Object b, Object c, Object d, Object e, Object f, Object g, Object h) {
        this.data = new TTuple11(a, b, c, d, e, f, g, h, null, null, null);
    }

    public TSHTuple(Object a, Object b, Object c, Object d, Object e, Object f, Object g, Object h, Object i) {
        this.data = new TTuple11(a, b, c, d, e, f, g, h, i, null, null);
    }


    public TSHTuple(Object a, Object b, Object c, Object d, Object e, Object f, Object g, Object h, Object i, Object j) {
        this.data = new TTuple11(a, b, c, d, e, f, g, h, i, j, null);
    }

    public TSHTuple(Object a, Object b, Object c, Object d, Object e, Object f, Object g, Object h, Object i, Object j, Object k) {
        this.data = new TTuple11(a, b, c, d, e, f, g, h, i, j, k);
    }

    public TTuple11 getData() {
        return data;
    }

    public void setData(TTuple11 data) {
        this.data = data;
    }


    public void add(Object object) {
        TTuple11<Object, Object, Object, Object, Object, Object, Object, Object, Object, Object, Object> data = this.getData();
        if (data.getFirst() == null) {
            data.setFirst(object);
            this.setData(data);
            return;
        }
        if (data.getSecond() == null) {
            data.setSecond(object);
            this.setData(data);
            return;
        }
        if (data.getThird() == null) {
            data.setThird(object);
            this.setData(data);
            return;
        }
        if (data.getFourth() == null) {
            data.setFourth(object);
            this.setData(data);
            return;
        }
        if (data.getFifth() == null) {
            data.setFifth(object);
            this.setData(data);
            return;
        }
        if (data.getSixth() == null) {
            data.setSixth(object);
            this.setData(data);
            return;
        }
        if (data.getElevent() == null) {
            data.setElevent(object);
            this.setData(data);
            return;
        }
        if (data.getEight() == null) {
            data.setEight(object);
            this.setData(data);
            return;
        }
        if (data.getNine() == null) {
            data.setNine(object);
            this.setData(data);
            return;
        }
        if (data.getTen() == null) {
            data.setTen(object);
            this.setData(data);
            return;
        }
    }

    public TSHTuple(TSHTuple tuple) {
        this.data = new TTuple11(null, null, null, null, null, null, null, null, null, null, null);
        if (tuple == null || tuple.getData() == null) {
            return;
        }

        TTuple11 elevent = tuple.getData();
        if (elevent.getFirst() != null) {
            this.data.setFirst(elevent.getFirst());
            this.setData(data);
        } else {
            return;
        }
        if (elevent.getSecond() != null) {
            this.data.setSecond(elevent.getSecond());
            this.setData(data);
        } else {
            return;
        }
        if (elevent.getThird() != null) {
            this.data.setThird(elevent.getThird());
            this.setData(data);
        } else {
            return;
        }
        if (elevent.getFourth() != null) {
            this.data.setFourth(elevent.getFourth());
            this.setData(data);
        } else {
            return;
        }
        if (elevent.getFifth() != null) {
            this.data.setFifth(elevent.getFifth());
            this.setData(data);
        } else {
            return;
        }
        if (elevent.getSixth() != null) {
            this.data.setSixth(elevent.getSixth());
            this.setData(data);
        } else {
            return;
        }
        if (elevent.getElevent() != null) {
            this.data.setElevent(elevent.getElevent());
            this.setData(data);
        } else {
            return;
        }
        if (elevent.getEight() != null) {
            this.data.setEight(elevent.getEight());
            this.setData(data);
        } else {
            return;
        }
        if (elevent.getNine() != null) {
            this.data.setNine(elevent.getNine());
            this.setData(data);
        } else {
            return;
        }
        if (elevent.getTen() != null) {
            this.data.setTen(elevent.getTen());
            this.setData(data);
        } else {
            return;
        }
    }


    public static String getString(TSHTuple tuple) {
        StringBuilder sb = new StringBuilder("  ");
        TTuple11 elevent = tuple.getData();
        if (elevent.getFirst() != null) {
            sb.append(elevent.getFirst()).append("  ");
        }else{
            return sb.toString();
        }

        if (elevent.getSecond() != null) {
            sb.append(elevent.getSecond()).append("  ");
        }else{
            return sb.toString();
        }

        if (elevent.getThird() != null) {
            sb.append(elevent.getThird()).append("  ");
        }else{
            return sb.toString();
        }

        if (elevent.getFourth() != null) {
            sb.append(elevent.getFourth()).append("  ");
        }else{
            return sb.toString();
        }

        if (elevent.getFifth() != null) {
            sb.append(elevent.getFifth()).append("  ");
        }else{
            return sb.toString();
        }
        if (elevent.getSixth() != null) {
            sb.append(elevent.getSixth()).append("  ");
        }else{
            return sb.toString();
        }
        if (elevent.getElevent() != null) {
            sb.append(elevent.getElevent()).append("  ");
        }else{
            return sb.toString();
        }

        if (elevent.getEight() != null) {
            sb.append(elevent.getEight()).append("  ");
        }else{
            return sb.toString();
        }
        if (elevent.getNine() != null) {
            sb.append(elevent.getNine()).append("  ");
        }else{
            return sb.toString();
        }
        if (elevent.getTen() != null) {
            sb.append(elevent.getTen()).append("  ");
        }else{
            return sb.toString();
        }
        return sb.toString();
    }


}
