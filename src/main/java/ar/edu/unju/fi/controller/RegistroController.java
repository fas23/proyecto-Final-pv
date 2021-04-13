package ar.edu.unju.fi.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.edu.unju.fi.model.RegistroTracking;
import ar.edu.unju.fi.model.Tripulante;
import ar.edu.unju.fi.model.Vehiculo;
import ar.edu.unju.fi.service.ILocalidadService;
import ar.edu.unju.fi.service.IRegistroTrackingService;
import ar.edu.unju.fi.service.ITripulanteService;
import ar.edu.unju.fi.service.IVehiculoService;

//@Secured("registrador")
@Controller
@RequestMapping("/views/registros")
public class RegistroController {

	@Autowired
	private IVehiculoService vehiculoService;
	
	@Autowired
	private ILocalidadService localidadService;
	
	@Autowired
	private ITripulanteService tripulanteService;
	
	@Autowired
	private IRegistroTrackingService registroTrackingService;

	@Autowired
	private Vehiculo unVehiculo;

	@Autowired
	private Tripulante tripulante;
	
	@GetMapping("/")
	public String registroPatenteBuscar(Model model) {
		model.addAttribute("titulo", "Busqueda de Vehiculo");
		model.addAttribute("vehiculo", new Vehiculo());
		model.addAttribute("btnSig", false);
		
		//carga la pagina registroVehiculo.html
		return "/views/registros/registroVehiculo";

	}

	@PostMapping("/vehiculoBuscado")
	public String buscarVehiculo(@ModelAttribute Vehiculo vehiculo, Model model) {
		
		model.addAttribute("titulo", "Busqueda de Vehiculo");
		unVehiculo.setPatente(vehiculo.getPatente());

		Vehiculo vehiculoEncontrado = vehiculoService.buscarVehiculo(vehiculo.getPatente());
		if (vehiculoEncontrado == null) {
			model.addAttribute("mensaje", "La patente no esta registrada");
		} else {
			model.addAttribute("mensaje", "La patente esta registrada");
		}

		//carga la pagina registroVehiculo.html y muestra el resultado de la busqueda
		return "/views/registros/registroVehiculo";
	}

	@GetMapping("/vehiculoForm")
	public String vehiculoFormulario(@ModelAttribute Vehiculo vehiculo, Model model) {
		model.addAttribute("titulo", "Registro Vehiculo");
		model.addAttribute("vehiculo", new Vehiculo());
		return "/views/registros/formVehiculo";
	}

	@PostMapping("/registroTracking")
	public String guardarVehiculo(@ModelAttribute Vehiculo vehiculo, Model model) {

		vehiculoService.guardar(vehiculo);
		return "redirect:/views/registros/registroTracking";
	}

	@GetMapping("/registroTracking")
	public String siguienteRegistro(Model model) {
		model.addAttribute("registro", new RegistroTracking());
		
		model.addAttribute("tripulanteDelForm", tripulante);

		//busca los tripulantes que estan en el listado auxiliar
		model.addAttribute("tripulantes", tripulanteService.buscarTodosTripulantes());
		
		model.addAttribute("listaVehiculo", vehiculoService.buscarVehiculo(unVehiculo.getPatente()));
		
		model.addAttribute("listaLocalidad", localidadService.listarTodos());

		System.out.println("patente" + unVehiculo.getPatente());
		return "/views/registros/registroTracking";
	}
	@PostMapping("/registroFinalizado")
	public String guardarRegistroTracking(@ModelAttribute("registro") RegistroTracking unRegistro, Model model) {
		unRegistro.setTripulantes(tripulanteService.buscarTodosTripulantes());
		unRegistro.setVehiculo(vehiculoService.buscarVehiculo(unVehiculo.getPatente()));
		try {
			registroTrackingService.guardar(unRegistro);
		} catch (Exception e) {
			model.addAttribute("formTripulanteErrorMessage", e.getMessage());
			System.out.println("Error Registro: " +e.getMessage());
		}
		
		
		tripulanteService.borrarTodosTripulantes();
		
	
		return "redirect:/views/registros/listado";
	}
	
	@GetMapping("/listado")
	public String listarRegistros(Model model) {
		model.addAttribute("listaRegistros", registroTrackingService.listarTodos());
		return "/views/registros/listar";
	}
	
	
	@PostMapping("/buscarTripulante")
	public String buscarTripulante(@ModelAttribute("tripulanteDelForm") Tripulante tripulante, Model model) throws Exception{
		try {
			
			//busca el tripulante en la base de datos
			Tripulante tripulanteEncontrado = tripulanteService.buscarTripulanteDocumento(tripulante.getDocumento());
			try {
				//si encuentra el tripulante lo guarda en una lista auxiliar
				tripulanteService.guardarTripulanteEncontrado(tripulanteEncontrado);
			} catch (Exception e) {
				model.addAttribute("formTripulanteErrorMessage", e.getMessage());
			}
			
		} catch (Exception e) {
			model.addAttribute("formTripulanteErrorMessage", e.getMessage());
			System.out.println("error " + e.getMessage());
		}
		
		/**
		 * retorno el metodo donde se carga el registroTracking ya que al cerrar el modal si el tripulante no exixte
		 * se perderia entonces retorno el model con el error 
		 */
		
		return siguienteRegistro(model); 
	}
	
	
	@GetMapping("/registroTripulante")
	public String formularioTripulante(Model model) {
		model.addAttribute("titulo", "Registro Tripulante");
		model.addAttribute("formTripulante", tripulante);
		
		return "/views/tripulantes/formTripulante";
	}
	
	@PostMapping("/formTripulante")
	public String guardarTripulante(@ModelAttribute("formTripulante") Tripulante tripulante, Model model) {
		try {
			tripulanteService.guardar(tripulante);
			return "redirect:/views/registros/registroTracking";
		} catch (Exception e) {
			return "/views/tripulantes/formTripulante";
		}
		
		
	}

}
