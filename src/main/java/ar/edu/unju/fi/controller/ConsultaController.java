package ar.edu.unju.fi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import ar.edu.unju.fi.model.RegistroTracking;
import ar.edu.unju.fi.model.Tripulante;
import ar.edu.unju.fi.service.ILocalidadService;
import ar.edu.unju.fi.service.IRegistroTrackingService;
import ar.edu.unju.fi.service.ITripulanteService;
import ar.edu.unju.fi.utils.BuscarVehiculo;

//@Secured("consultor")
@Controller
@RequestMapping("/views/consultas")
public class ConsultaController {

	@Autowired
	private Tripulante unTripulante;

	@Autowired
	private IRegistroTrackingService registroTrackingService;

	@Autowired
	private ITripulanteService tripulanteService;

	@Autowired
	private ILocalidadService localidadService;

	@Autowired
	private BuscarVehiculo unVehiculo;

	@GetMapping("/")
	public String consultasForm(Model model) {
		model.addAttribute("tripulanteForm", unTripulante);
		return "/views/consultas/consultasForm";
	}

	@PostMapping("/tripulanteBuscado")
	public String buscarTripulanteDocumento(@ModelAttribute("tripulanteForm") Tripulante tripulante, Model model)
			throws Exception {

		unTripulante = tripulanteService.buscarTripulanteDocumento(tripulante.getDocumento());

		List<RegistroTracking> listaEncontrada = registroTrackingService.buscarTripulantes(tripulante.getDocumento());
		try {
			model.addAttribute("titulo", "Datos del Tripulante");
			model.addAttribute("tripulanteEncontrado", unTripulante);
			model.addAttribute("listaTripulantes", listaEncontrada);
			model.addAttribute("recorrido", "Recorrido Tripulante");
			System.out.println("Lista" + listaEncontrada);
		} catch (Exception e) {
			model.addAttribute("mensaje", e.getMessage());
			System.out.println("Error" + e.getMessage());
		}

		return "/views/consultas/consultaTripulanteForm";
	}

	@GetMapping("/cargarRegistro")
	public String formConsultaRegistroFecha(Model model) {
		// model.addAttribute("registroForm", new RegistroTracking());
		model.addAttribute("registroForm", new BuscarVehiculo());
		model.addAttribute("listaLocalidad", localidadService.listarTodos());
		return "/views/consultas/consultaVehiculoLocalidadFechaHora";
	}

	@PostMapping("/buscarRegistroFechaHora")
	public String buscar(@ModelAttribute("registroForm") BuscarVehiculo registro, Model model) {

		this.unVehiculo = registro;
		
		model.addAttribute("listaFecha", registroTrackingService.buscarRegistroFechaHora(registro.getFechaDesde(),
				registro.getFechaHasta(), registro.getLocalidad().getNombre()));
		model.addAttribute("listaLocalidad", localidadService.listarTodos());

		return "/views/consultas/consultaVehiculoLocalidadFechaHora";

	}

	@GetMapping("/registro/{id}")
	public String listaTripulantes(@PathVariable("id") Long idRegistro,@ModelAttribute("registroForm") BuscarVehiculo registro, Model model) throws Exception {

		RegistroTracking registroEncontrado = registroTrackingService.buscarPorId(idRegistro);
		model.addAttribute("tripulantes", registroTrackingService.obtenerTodosTripulantesVehiculo(registroEncontrado));
		model.addAttribute("listaFecha", registroTrackingService.buscarRegistroFechaHora(this.unVehiculo.getFechaDesde(),
						this.unVehiculo.getFechaHasta(), this.unVehiculo.getLocalidad().getNombre()));
		
		model.addAttribute("listaLocalidad", localidadService.listarTodos());

		return "/views/consultas/consultaVehiculoLocalidadFechaHora";
	}

}
