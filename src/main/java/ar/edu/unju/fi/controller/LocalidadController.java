package ar.edu.unju.fi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.edu.unju.fi.model.Localidad;
import ar.edu.unju.fi.service.ILocalidadService;

@Controller
@RequestMapping("/views/localidades")
public class LocalidadController {
	
	@Autowired
	private ILocalidadService localidadService;
	
	@GetMapping("/")
	public String listar(Model model) {
		
		List<Localidad> listarLocalidades = localidadService.listarTodos();
		model.addAttribute("titulo", "Listado de Localidades");
		model.addAttribute("lista", listarLocalidades);
		
		return "views/localidades/listar";
	}
	
	@GetMapping("/create")
	public String crear(Model model) {
		Localidad localidad = new Localidad();

		model.addAttribute("titulo", "Formulario: Nueva Localidad");
		model.addAttribute("localidad", localidad);

		return "/views/localidades/frmLocalidad";
	}

	@PostMapping("/save")
	public String guardar(@Valid @ModelAttribute Localidad localidad, BindingResult result, Model model) {

		Boolean auxLocalidad;

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario: Nueva Localidad");
			model.addAttribute("localidad", localidad);
			return "/views/localidades/frmLocalidad";
		} else {

			auxLocalidad = localidadService.buscarPorNombre(localidad.getNombre());

			if (auxLocalidad) {
				model.addAttribute("localidadEncontrada", "La Localidad ya existe en la base de datos");
				return crear(model);
			} else {
				try {
					localidadService.guardar(localidad);

				} catch (Exception e) {
					System.out.println("error");
				}
			}

		}

		return "redirect:/views/localidades/";

	}

	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") Long idLocalidad, Model model) {

		Localidad localidad= localidadService.buscarPorId(idLocalidad);

		model.addAttribute("titulo", "Formulario: Editar Usuario");
		model.addAttribute("localidad", localidad);

		return "/views/localidades/frmLocalidad";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long idUsuario) {
		localidadService.eliminar(idUsuario);
		return "redirect:/views/localidades/";
	}

	@GetMapping("/cancelar")
	public String cancelar(Model model) {
		return "redirect:/views/localidades/";
		// devolver el metodo del formulario
		// return crear(model);
	}

	
}
