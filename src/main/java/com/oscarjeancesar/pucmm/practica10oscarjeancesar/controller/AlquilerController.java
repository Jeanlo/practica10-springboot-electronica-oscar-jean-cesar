package com.oscarjeancesar.pucmm.practica10oscarjeancesar.controller;

import com.oscarjeancesar.pucmm.practica10oscarjeancesar.model.Alquiler;
import com.oscarjeancesar.pucmm.practica10oscarjeancesar.model.Cliente;
import com.oscarjeancesar.pucmm.practica10oscarjeancesar.model.Equipo;
import com.oscarjeancesar.pucmm.practica10oscarjeancesar.model.Familia;
import com.oscarjeancesar.pucmm.practica10oscarjeancesar.service.ClienteServices;
import com.oscarjeancesar.pucmm.practica10oscarjeancesar.service.EquipoServices;
import com.oscarjeancesar.pucmm.practica10oscarjeancesar.service.AlquilerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/alquiler")
public class AlquilerController {

    @Autowired
    AlquilerServices alquilerServices;

    @Autowired
    EquipoServices equipoServices;

    @Autowired
    ClienteServices clienteServices;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping("/")
    public String index(Model model, Locale locale, Principal principal) {

        boolean sizeAlquiler = alquilerServices.listadoAlquiler().size() > 0;
        model.addAttribute("sizeAlquiler", sizeAlquiler);

        model.addAttribute("titulo", messageSource.getMessage("titulo", null, locale));
        model.addAttribute("mensaje", messageSource.getMessage("mensaje", null, locale));

        model.addAttribute("linkInicio", messageSource.getMessage("linkInicio", null, locale));
        model.addAttribute("linkClientes", messageSource.getMessage("linkClientes", null, locale));
        model.addAttribute("linkEquipos", messageSource.getMessage("linkEquipos", null, locale));
        model.addAttribute("linkFamilia", messageSource.getMessage("linkFamilia", null, locale));
        model.addAttribute("linkAlquiler", messageSource.getMessage("linkAlquiler", null, locale));
        model.addAttribute("linkGraficas", messageSource.getMessage("linkGraficas", null, locale));
        model.addAttribute("linkUsuario", messageSource.getMessage("linkUsuario", null, locale));

        model.addAttribute("fechaCreacion", messageSource.getMessage("fechaCreacion", null, locale));
        model.addAttribute("fechaEntrega", messageSource.getMessage("fechaEntrega", null, locale));
        model.addAttribute("clienteMensaje", messageSource.getMessage("clienteMensaje", null, locale));
        model.addAttribute("total", messageSource.getMessage("total", null, locale));
        model.addAttribute("ver", messageSource.getMessage("ver", null, locale));

        model.addAttribute("botonCrear", messageSource.getMessage("botonCrear", null, locale));
        model.addAttribute("usuario", principal.getName());
        model.addAttribute("alquileres", alquilerServices.listadoAlquiler());

        return "alquiler";
    }

    @RequestMapping("/crear")
    public String crearAlquilerGET(Model model, Locale locale, Principal principal) {

        model.addAttribute("titulo", messageSource.getMessage("titulo", null, locale));
        model.addAttribute("mensaje", messageSource.getMessage("mensaje", null, locale));

        model.addAttribute("linkInicio", messageSource.getMessage("linkInicio", null, locale));
        model.addAttribute("linkClientes", messageSource.getMessage("linkClientes", null, locale));
        model.addAttribute("linkEquipos", messageSource.getMessage("linkEquipos", null, locale));
        model.addAttribute("linkFamilia", messageSource.getMessage("linkFamilia", null, locale));
        model.addAttribute("linkAlquiler", messageSource.getMessage("linkAlquiler", null, locale));
        model.addAttribute("linkGraficas", messageSource.getMessage("linkGraficas", null, locale));
        model.addAttribute("linkUsuario", messageSource.getMessage("linkUsuario", null, locale));

        model.addAttribute("tituloAlquiler", messageSource.getMessage("tituloAlquiler", null, locale));
        model.addAttribute("mensajeCrearAlquiler", messageSource.getMessage("mensajeCrearAlquiler", null, locale));
        model.addAttribute("placeholderNombreEquipo", messageSource.getMessage("placeholderNombreEquipo", null, locale));

        model.addAttribute("fechaCreacion", messageSource.getMessage("fechaCreacion", null, locale));
        model.addAttribute("fechaEntrega", messageSource.getMessage("fechaEntrega", null, locale));
        model.addAttribute("clienteMensaje", messageSource.getMessage("clienteMensaje", null, locale));
        model.addAttribute("total", messageSource.getMessage("total", null, locale));
        model.addAttribute("ver", messageSource.getMessage("ver", null, locale));

        model.addAttribute("botonCrear", messageSource.getMessage("botonCrear", null, locale));
        model.addAttribute("usuario", principal.getName());

        model.addAttribute("clientes", clienteServices.getListadoDeClientes());
        model.addAttribute("equipos", equipoServices.listadoEquipos());

        return "agregarAlquiler";
    }

    @RequestMapping(value = "/crear", method = RequestMethod.POST)
    public String crearAlquilerPOST(
            @RequestParam(value = "cliente", required = false) long cliente,
            @RequestParam(value = "fechaCreacion", required = false) String fechaCreacion,
            @RequestParam(value = "fechaEntrega", required = false) String fechaEntrega,
            @RequestParam(value = "equipos", required = false) List<Long> equipos) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Date dateFechaCreacion = formatter.parse(fechaCreacion);
        Date dateFechaEntrega = formatter.parse(fechaEntrega);

        Cliente cliente1 = clienteServices.getClientePorID(cliente);
        List<Equipo> equipoList = new ArrayList<>();

        for (Long equipo: equipos) {
            equipoList.add(equipoServices.getEquipoPorID(equipo));
            System.out.print(equipo);
        }

        alquilerServices.crearAlquiler(new Alquiler(dateFechaCreacion, dateFechaEntrega, cliente1, equipoList, equipoList, 0));

        return "redirect:/alquiler/";
    }

}