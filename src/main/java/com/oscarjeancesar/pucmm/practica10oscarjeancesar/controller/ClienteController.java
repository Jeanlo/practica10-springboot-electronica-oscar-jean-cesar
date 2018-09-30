package com.oscarjeancesar.pucmm.practica10oscarjeancesar.controller;

import com.oscarjeancesar.pucmm.practica10oscarjeancesar.model.Cliente;
import com.oscarjeancesar.pucmm.practica10oscarjeancesar.model.Rol;
import com.oscarjeancesar.pucmm.practica10oscarjeancesar.model.Usuario;
import com.oscarjeancesar.pucmm.practica10oscarjeancesar.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    ClienteServices clienteServices;

    @Autowired
    StorageService storageService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/")
    public String index(Model model, Locale locale, Principal principal) {

        model.addAttribute("titulo", messageSource.getMessage("titulo", null, locale));
        model.addAttribute("mensaje", messageSource.getMessage("mensaje", null, locale));

        model.addAttribute("linkInicio", messageSource.getMessage("linkInicio", null, locale));
        model.addAttribute("linkClientes", messageSource.getMessage("linkClientes", null, locale));
        model.addAttribute("linkEquipos", messageSource.getMessage("linkEquipos", null, locale));
        model.addAttribute("linkFamilia", messageSource.getMessage("linkFamilia", null, locale));
        model.addAttribute("linkAlquiler", messageSource.getMessage("linkAlquiler", null, locale));
        model.addAttribute("linkGraficas", messageSource.getMessage("linkGraficas", null, locale));
        model.addAttribute("linkUsuario", messageSource.getMessage("linkUsuario", null, locale));

        model.addAttribute("placeholderNombreCliente", messageSource.getMessage("placeholderNombreCliente", null, locale));
        model.addAttribute("placeholderCedula", messageSource.getMessage("placeholderCedula", null, locale));
        model.addAttribute("placerholderTelefono", messageSource.getMessage("placerholderTelefono", null, locale));
        model.addAttribute("placerholderFotografia", messageSource.getMessage("placerholderFotografia", null, locale));
        model.addAttribute("botonCrear", messageSource.getMessage("botonCrear", null, locale));

        model.addAttribute("usuario", principal.getName());
        model.addAttribute("acciones", messageSource.getMessage("acciones", null, locale));
        model.addAttribute("acciones2", messageSource.getMessage("acciones2", null, locale));
        model.addAttribute("clientes", clienteServices.getListadoDeClientes());

        return "clientes";
    }

    @RequestMapping(value = "/crear")
    public String crearClienteGET(Model model, Locale locale, Principal principal) {

        model.addAttribute("titulo", messageSource.getMessage("titulo", null, locale));
        model.addAttribute("mensaje", messageSource.getMessage("mensaje", null, locale));

        model.addAttribute("linkInicio", messageSource.getMessage("linkInicio", null, locale));
        model.addAttribute("linkClientes", messageSource.getMessage("linkClientes", null, locale));
        model.addAttribute("linkEquipos", messageSource.getMessage("linkEquipos", null, locale));
        model.addAttribute("linkFamilia", messageSource.getMessage("linkFamilia", null, locale));
        model.addAttribute("linkAlquiler", messageSource.getMessage("linkAlquiler", null, locale));
        model.addAttribute("linkGraficas", messageSource.getMessage("linkGraficas", null, locale));
        model.addAttribute("linkUsuario", messageSource.getMessage("linkUsuario", null, locale));

        model.addAttribute("placeholderNombreCliente", messageSource.getMessage("placeholderNombreCliente", null, locale));
        model.addAttribute("placeholderCedula", messageSource.getMessage("placeholderCedula", null, locale));
        model.addAttribute("placerholderTelefono", messageSource.getMessage("placerholderTelefono", null, locale));
        model.addAttribute("placerholderFotografia", messageSource.getMessage("placerholderFotografia", null, locale));
        model.addAttribute("botonCrear", messageSource.getMessage("botonCrear", null, locale));

        model.addAttribute("tituloCrearCliente", messageSource.getMessage("tituloCrearCliente", null, locale));
        model.addAttribute("mensajeCrearCliente", messageSource.getMessage("mensajeCrearCliente", null, locale));

        model.addAttribute("usuario", principal.getName());

        return "agregarCliente";
    }

    @RequestMapping(value = "/crear", method = RequestMethod.POST)
    public String crearClientePOST(
            @RequestParam(value = "nombre", required = false) String nombre,
            @RequestParam(value = "cedula", required = false) String cedula,
            @RequestParam(value = "telefono", required = false) String telefono,
            @RequestParam(value = "fotografia", required = false) String fotografia) {

        clienteServices.crearCliente(new Cliente(nombre, cedula, telefono, fotografia));

        return "redirect:/cliente/";
    }

    @RequestMapping(value = "/eliminar-cliente/{id}", method = RequestMethod.POST)
    public String eliminarClientePOST(@PathVariable("id") long id){

        clienteServices.eliminarCliente(id);

        return "redirect:/cliente/";
    }

    @RequestMapping(value = "/modificar-cliente/{id}")
    public String modificarClienteGET(Model model, Locale locale, Principal principal, @PathVariable("id") long idCliente) {

        model.addAttribute("titulo", messageSource.getMessage("titulo", null, locale));
        model.addAttribute("mensaje", messageSource.getMessage("mensaje", null, locale));

        model.addAttribute("linkInicio", messageSource.getMessage("linkInicio", null, locale));
        model.addAttribute("linkClientes", messageSource.getMessage("linkClientes", null, locale));
        model.addAttribute("linkEquipos", messageSource.getMessage("linkEquipos", null, locale));
        model.addAttribute("linkFamilia", messageSource.getMessage("linkFamilia", null, locale));
        model.addAttribute("linkAlquiler", messageSource.getMessage("linkAlquiler", null, locale));
        model.addAttribute("linkGraficas", messageSource.getMessage("linkGraficas", null, locale));
        model.addAttribute("linkUsuario", messageSource.getMessage("linkUsuario", null, locale));

        model.addAttribute("placeholderNombreCliente", messageSource.getMessage("placeholderNombreCliente", null, locale));
        model.addAttribute("placeholderCedula", messageSource.getMessage("placeholderCedula", null, locale));
        model.addAttribute("placerholderTelefono", messageSource.getMessage("placerholderTelefono", null, locale));
        model.addAttribute("placerholderFotografia", messageSource.getMessage("placerholderFotografia", null, locale));
        model.addAttribute("botonCrear", messageSource.getMessage("botonCrear", null, locale));

        model.addAttribute("acciones2", messageSource.getMessage("acciones2", null, locale));
        model.addAttribute("tituloModificarCliente", messageSource.getMessage("tituloModificarCliente", null, locale));
        model.addAttribute("mensajeModificarCliente", messageSource.getMessage("mensajeModificarCliente", null, locale));

        model.addAttribute("usuario", principal.getName());

        // Parámetros del cliente
        Cliente clienteAModificar = clienteServices.getClientePorID(idCliente);
        model.addAttribute("cliente", clienteAModificar);

        return "modificarCliente";
    }

    @RequestMapping(value = "/modificar/{id}", method = RequestMethod.POST)
    public String modificarClientePOST(@PathVariable("id") long id,
       @RequestParam(value = "nombre", required = false) String nombre,
       @RequestParam(value = "cedula", required = false) String cedula,
       @RequestParam(value = "telefono", required = false) String telefono,
       @RequestParam(value = "fotografia", required = false) String fotografia){

        Cliente cliente = clienteServices.getClientePorID(id);
        cliente.setNombre(nombre);
        cliente.setCedula(cedula);
        cliente.setTelefono(telefono);
        cliente.setFotografia(fotografia);
        clienteServices.crearCliente(cliente);

        return "redirect:/cliente/";
    }


}