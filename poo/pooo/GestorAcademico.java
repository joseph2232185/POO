import java.util.ArrayList;
import java.util.HashMap;

public class GestorAcademico implements ServiciosAcademicosI {
    private ArrayList<Estudiante> estudiantes;
    private ArrayList<Curso> cursos;
    private HashMap<Curso, ArrayList<Estudiante>> estudiantesPorCurso;

    public GestorAcademico() {
        this.estudiantes = new ArrayList<>();
        this.cursos = new ArrayList<>();
        this.estudiantesPorCurso = new HashMap<>();
    }

    @Override
    public void matricularEstudiante(Estudiante estudiante) {
        if (!estudiantes.contains(estudiante)) {
            estudiantes.add(estudiante);
        }
    }

    @Override
    public void agregarCurso(Curso curso) {
        if (!cursos.contains(curso)) {
            cursos.add(curso);
            estudiantesPorCurso.put(curso, new ArrayList<>());
        }
    }

    @Override
    public void inscribirEstudianteCurso(Estudiante estudiante, int idCurso) throws EstudianteYaInscritoException {
        Curso curso = obtenerCursoPorId(idCurso);
        if (curso != null) {
            ArrayList<Estudiante> inscritos = estudiantesPorCurso.get(curso);
            if (inscritos.contains(estudiante)) {
                throw new EstudianteYaInscritoException("el estudiante ya está inscrito en el curso ");
            }
            inscritos.add(estudiante);
        }
    }

    @Override
    public void desinscribirEstudianteCurso(int idEstudiante, int idCurso) throws EstudianteNoInscritoEnCursoException {
        Curso curso = obtenerCursoPorId(idCurso);
        if (curso != null) {
            ArrayList<Estudiante> inscritos = estudiantesPorCurso.get(curso);
            Estudiante estudiante = obtenerEstudiantePorId(idEstudiante);
            if (inscritos.contains(estudiante)) {
                inscritos.remove(estudiante);
            } else {
                throw new EstudianteNoInscritoEnCursoException("El estudiante no está inscrito en el curso ");
            }
        }
    }

    public void removerEstudiante(int id) {
        estudiantes.removeIf(estudiante -> estudiante.getId() == id);
        for (Curso curso : estudiantesPorCurso.keySet()) {
            estudiantesPorCurso.get(curso).removeIf(estudiante -> estudiante.getId() == id);
        }
    }

    public void removerCurso(int id) {
        cursos.removeIf(curso -> curso.getId() == id);
        estudiantesPorCurso.keySet().removeIf(curso -> curso.getId() == id);
    }

    public Estudiante obtenerEstudiantePorId(int id) {
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getId() == id) {
                return estudiante;
            }
        }
        return null;
    }

    public Curso obtenerCursoPorId(int id) {
        for (Curso curso : cursos) {
            if (curso.getId() == id) {
                return curso;
            }
        }
        return null;
    }

    public ArrayList<Estudiante> listarEstudiantesPorEstado(Estudiante.Estado estado) {
        ArrayList<Estudiante> estudiantesPorEstado = new ArrayList<>();
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getEstado() == estado) {
                estudiantesPorEstado.add(estudiante);
            }
        }
        return estudiantesPorEstado;
    }


    public ArrayList<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public ArrayList<Curso> getCursos() {
        return cursos;
    }

    public ArrayList<Estudiante> getEstudiantesPorCurso(Curso curso) {
        return this.estudiantesPorCurso.get(curso);
    }
}
