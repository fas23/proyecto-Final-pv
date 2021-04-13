package ar.edu.unju.fi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.edu.unju.fi.model.Usuario;
import ar.edu.unju.fi.service.IUsuarioService;

//@Secured("role_administrador")
@Controller
@RequestMapping("/views/usuarios")
public class UsuarioController {

	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private Usuario unUsuario;

	@GetMapping("/")
	public String listar(Model model) {

		List<Usuario> listarUsuarios = usuarioService.listarTodos();
		model.addAttribute("titulo", "Lista Usuarios");
		model.addAttribute("lista", listarUsuarios);

		return "/views/usuarios/listar";
	}

	@GetMapping("/create")
	public String crear(Model model) {
		Usuario usuario = new Usuario();

		model.addAttribute("titulo", "Formulario: Nuevo Usuario");
		model.addAttribute("usuario", usuario);

		return "/views/usuarios/frmUsuario";
	}

	@PostMapping("/save")
	public String guardar(@Valid @ModelAttribute Usuario usuario, BindingResult result, Model model) {

		Boolean auxUsuario;

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario: Nuevo Usuario");
			model.addAttribute("usuario", usuario);
			return "/views/usuarios/frmUsuario";
		} else {

			auxUsuario = usuarioService.buscarPorNombreApellido(usuario.getNombreReal(), usuario.getApellidoReal());

			if (auxUsuario) {
				model.addAttribute("usuarioEncontrado", "El usuario ya existe en la base de datos");
				System.out.println("usuario encontrado");
				return crear(model);
			} else {

				try {

					usuarioService.guardar(usuario);
					System.out.println("Usuario guardado con exito");

				} catch (Exception e) {
					System.out.println("error");
				}
			}

		}

		return "redirect:/views/usuarios/";

	}

	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") Long idUsuario, Model model) throws Exception{
		
		try {
			Usuario usuarioEncontrado = usuarioService.encontrarUsuario(idUsuario);
			model.addAttribute("usuario", usuarioEncontrado);
			model.addAttribute("titulo", "Formulario: Editar Usuario");
			model.addAttribute("editMode", "true");
		} catch (Exception e) {
			model.addAttribute("formUsuarioErrorMessage", e.getMessage());
			model.addAttribute("usuario", unUsuario);
			model.addAttribute("editMode", "false");
		}
	

		return "/views/usuarios/frmUsuario";
	}
	
	@PostMapping("/edit")
	public String postEditar(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result, Model model) throws Exception{
		
		if(result.hasErrors()) {
			//se envia el formulario
			model.addAttribute("usuario", usuario);
			//modo de edicion esta en verdadero
			model.addAttribute("editMode", "true");
		}else {
			try {
				//si no hay errores el usuario modificado se guarda
				usuarioService.modificar(usuario);
				model.addAttribute("editMode", "false");
				return "redirect:/views/usuarios/";
			} catch (Exception e) {
				//si ocurre un error se captura y se lo envia a la vista 
				model.addAttribute("formUsuarioErrorMessage", e.getMessage());
				model.addAttribute("usuario", usuario);
				model.addAttribute("editMode", "true");
			}
		}

		
		return "/views/usuarios/frmUsuario";
	}


	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long idUsuario) {
		usuarioService.eliminar(idUsuario);
		return "redirect:/views/usuarios/";
	}

	@GetMapping("/cancelar")
	public String cancelar(Model model) {
		return "redirect:/views/usuarios/";
		// devolver el metodo del formulario
		// return crear(model);
	}

}
