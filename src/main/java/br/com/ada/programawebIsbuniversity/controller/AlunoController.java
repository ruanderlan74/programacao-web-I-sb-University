package br.com.ada.programawebIsbuniversity.controller;

import br.com.ada.programawebIsbuniversity.model.Aluno;
import br.com.ada.programawebIsbuniversity.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
public class AlunoController {

        @Autowired
        private AlunoService alunoService;

        @GetMapping("/alunos")
        public ModelAndView alunos() {
            ModelAndView modelAndView = new ModelAndView("alunos");
            List<Aluno> aluno = this.alunoService.listarTodos();
            modelAndView.addObject("alunos",aluno);
            return modelAndView;
        }

        @GetMapping("/aluno/add")
        public String addAluno(Model model, Aluno aluno) {
            model.addAttribute("add", Boolean.TRUE);
            model.addAttribute("aluno", Objects.nonNull(aluno) ? aluno : new Aluno());
            return "aluno-add";
        }

        @PostMapping("/aluno/add")
        public String criarAluno(@Valid @ModelAttribute("aluno") Aluno aluno,
                                   BindingResult result,
                                   Model model) {

            if(result.hasErrors()) {
                return addAluno(model, aluno);
            }

            this.alunoService.createAluno(aluno);
            return "redirect:/alunos";
        }

        @GetMapping("/aluno/{alunoId}/delete")
        public String deletarAluno(@PathVariable("alunoId") Long alunoId) {
            this.alunoService.removerAlunoPorId(alunoId);
            return "redirect:/alunos";
        }

        @GetMapping("/aluno/{alunoId}/edit")
        public String mostrarEdicaoAluno(Model model, @PathVariable("alunoId") Long alunoId) {
            Optional<Aluno> optionalAluno = this.alunoService.buscarAlunoPorId(alunoId);
            optionalAluno.ifPresent(aluno -> model.addAttribute("aluno", aluno));
            model.addAttribute("add", Boolean.FALSE);
            return "aluno-add";
        }

        @PutMapping("/aluno/{alunoId}/edit")
        public String editarAluno(@ModelAttribute("aluno") Aluno aluno,
                                    @PathVariable("alunoId") Long alunoId) {
            aluno.setId(alunoId);
            this.alunoService.createAluno(aluno);
            return "redirect:/alunos";
        }

    }

