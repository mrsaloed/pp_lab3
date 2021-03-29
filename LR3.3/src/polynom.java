import java.util.*;

public class polynom
{
    Scanner in = new Scanner(System.in);
    private ComplexNumber[] coeff;
    private int deg;
    public polynom(int n)
    {
        deg = in.nextInt();
        coeff = new ComplexNumber[deg+1];
        for(int i = 0; i < coeff.length; i++)
            coeff[i] = new ComplexNumber(0,0);
    }
    //Нулевой коэффициент - свободный член
    public void inputCoeff()
    {
        double re = 0;
        double im = 0;
        System.out.println("Введите коэффициенты: ");
        for(int i = 0; i < deg+1; i++)
        {
            System.out.print("Действительная часть coeff[" + i + "] = ");
            re = in.nextDouble();
            coeff[i].setRe(re);
            System.out.print("Мнимая часть coeff[" + i + "] = ");
            im = in.nextDouble();
            coeff[i].setIm(im);
            System.out.println();
        }
    }
    public void setCoeff(int index, ComplexNumber x)
    {
        coeff[index] = x;
    }
    public ComplexNumber getCoeff(int index)
    {
        return coeff[index];
    }
    public int getDeg()
    {
        return deg;
    }
    //Возвращает полином с суммой соотвутствующих коэффициентов
    public static polynom summ(polynom p1, polynom p2)
    {
        int tempDeg;
        if(p1.getDeg() < p2.getDeg())
            tempDeg = p1.getDeg();
        else
            tempDeg = p2.getDeg();
        polynom tempP = new polynom(tempDeg);
        for(int i = 0; i < tempDeg+1; i++)
            tempP.setCoeff(i, ComplexNumber.sum(p1.getCoeff(i), p2.getCoeff(i)));
        tempP.output();
        return tempP;
    }
    //Метод для инициализации полиномов
    public static polynom inputPolynom()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите степень полинома: ");
        int degP = in.nextInt();
        polynom p = new polynom(degP);
        p.inputCoeff();
        p.output();
        return p;
    }
    //Вывод полинома на экран
    public void output()
    {
        for(int i = 0; i < deg+1; i++)
        {
            System.out.print("("+coeff[i]+")" + "*" + "X(" + i + ")");
            if(i < deg)
                System.out.print("+");
        }
        System.out.println();
    }

    public static class ComplexNumber {


        private double re;//действительная часть
        private double im;//мнимая часть

        /**
         * конструктор
         */
        public ComplexNumber(double re, double im) {
            this.re = re;
            this.im = im;
        }

        /**
         * получить действительную часть
         */
        public double getRe() {
            return re;
        }

        public void setRe(double re){
            this.re = re;
        }

        public void setIm(double im){
            this.im=im;
        }

        /**
         * получить мнимую
         */
        public double getIm() {
            return im;
        }

        /**
         * вывод суммы комплексных чисел
         */
        public static ComplexNumber sum(ComplexNumber cn1, ComplexNumber cn2) {
            return new ComplexNumber(cn1.getRe() + cn2.getRe(), cn1.getIm() + cn2.getIm());
        }

        /**
         * умножение двух комплексных чиел
         */
        public static ComplexNumber multiply(ComplexNumber cn1, ComplexNumber cn2) {
            return new ComplexNumber(cn1.getRe() * cn2.getRe() - cn1.getIm() * cn2.getIm(), cn1.getRe() * cn2.getIm() + cn1.getIm() * cn2.getRe());
        }

        /**
         * знак
         */
        private String sign() {
            if (im > 0) return " + ";
            else return " - ";
        }

        @Override
        public String toString() {
            String string;
            if (im == 1 || im == -1) {
                if (re == 0) {
                    string = sign() + "i";
                } else {
                    string = Double.toString(re) + sign() + "i";
                }
            } else {
                string = Double.toString(re) + sign() + Double.toString(Math.abs(im)) + "i";
            }
            return string;
        }

        @Override
        public boolean equals(Object obj) {
            if (this.getClass() != obj.getClass() || obj == null)
                return false;
            return true;
        }
    }

    public static void main(String[] args)
    {
        int choose = 0;
        polynom p1 = null, p2 = null, pResult = null;
        Scanner in = new Scanner(System.in);
        do{
            System.out.println("1. Ввод");
            System.out.println("2. Получить сумму");
            System.out.println("3. Вывести полиномы");
            System.out.println("4. Выход");
            System.out.print("Введите номер пункта: ");
            choose = in.nextInt();
            switch(choose)
            {
                case 1:
                    System.out.println("Полином p1.");
                    p1 = inputPolynom();
                    System.out.println("Полином p2.");
                    p2 = inputPolynom();
                    break;
                case 2:
                    pResult = summ(p1, p2);
                    break;
                case 3:
                    System.out.println("Полином p1.");
                    p1.output();
                    System.out.println("Полином p2.");
                    p2.output();
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Неверный ввод. Попробуйте снова.");
            }
        }while(true);
    }
}
