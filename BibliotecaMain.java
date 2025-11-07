import java.util.ArrayList;
import java.util.Scanner;

// ===== Clase base =====
class Material {
    protected String titulo;
    protected String autor;
    protected boolean disponible = true;

    public Material(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }

    public void mostrarInfo() {
        System.out.println("Título: " + titulo + " | Autor: " + autor + " | Disponible: " + disponible);
    }
}

// ===== Herencia =====
class Libro extends Material {
    private String categoria;

    public Libro(String titulo, String autor, String categoria) {
        super(titulo, autor);
        this.categoria = categoria;
    }

    @Override
    public void mostrarInfo() {
        System.out.println("Libro: " + titulo + " - " + autor + " (" + categoria + ") | Disponible: " + disponible);
    }
}

// ===== Biblioteca =====
class Biblioteca {
    private ArrayList<Material> inventario = new ArrayList<>();

    public void agregarMaterial(Material m) {
        inventario.add(m);
    }

    public void mostrarInventario() {
        System.out.println("\n=== INVENTARIO ===");
        for (Material m : inventario) m.mostrarInfo();
    }

    public void prestar(String busqueda) {
        for (Material m : inventario) {
            if (m.titulo.toLowerCase().contains(busqueda.toLowerCase())) {
                if (m.disponible) {
                    m.disponible = false;
                    System.out.println("✅ Se prestó: " + m.titulo);
                } else {
                    System.out.println("⚠️ El material ya está prestado.");
                }
                return;
            }
        }
        System.out.println("❌ El material no existe en el inventario.");
    }

    public void devolver(String busqueda) {
        for (Material m : inventario) {
            if (m.titulo.toLowerCase().contains(busqueda.toLowerCase())) {
                if (!m.disponible) {
                    m.disponible = true;
                    System.out.println("📚 Devolución exitosa: " + m.titulo);
                } else {
                    System.out.println("⚠️ Ese material no estaba prestado.");
                }
                return;
            }
        }
        System.out.println("❌ Ese material no existe.");
    }

    public void calcularMulta(int dias) {
        System.out.println("💰 Multa a pagar: $" + (dias * 500));
    }
}

// ===== Programa Principal =====
public class BibliotecaMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Biblioteca biblioteca = new Biblioteca();

        biblioteca.agregarMaterial(new Libro("Lógica Matemática", "Aristóteles", "Filosofía"));
        biblioteca.agregarMaterial(new Libro("Programación en Java", "James Gosling", "Informática"));

        int opcion = 0;

        do {
            System.out.println("\n===== MENÚ BIBLIOTECA =====");
            System.out.println("1. Inventario");
            System.out.println("2. Préstamo");
            System.out.println("3. Devolución");
            System.out.println("4. Multas");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            if (!sc.hasNextInt()) { // Validación para evitar InputMismatchException
                System.out.println("⚠️ Debe ingresar un número válido.");
                sc.nextLine();
                continue;
            }

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    biblioteca.mostrarInventario();
                    break;
                case 2:
                    System.out.print("Ingrese el título a prestar: ");
                    biblioteca.prestar(sc.nextLine());
                    break;
                case 3:
                    System.out.print("Ingrese el título a devolver: ");
                    biblioteca.devolver(sc.nextLine());
                    break;
                case 4:
                    System.out.print("Ingrese días de atraso: ");

                    if (sc.hasNextInt()) {
                        biblioteca.calcularMulta(sc.nextInt());
                    } else {
                        System.out.println("⚠️ Ingrese un número válido.");
                        sc.nextLine();
                    }
                    break;
                case 5:
                    System.out.println("👋 Saliendo del sistema...");
                    break;
                default:
                    System.out.println("⚠️ Opción inválida.");
            }
        } while (opcion != 5);

        sc.close();
    }
}
