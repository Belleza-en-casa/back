package belleza.com.co.proyecto.belleza.controller;

import belleza.com.co.proyecto.belleza.core.dto.CategoriaDto;
import belleza.com.co.proyecto.belleza.core.enums.EstadoCategoria;
import belleza.com.co.proyecto.belleza.core.enums.EstadoItem;
import belleza.com.co.proyecto.belleza.core.response.ResponseHttp;
import belleza.com.co.proyecto.belleza.persistence.entity.CategoryEntity;
import belleza.com.co.proyecto.belleza.persistence.entity.ItemEntity;
import belleza.com.co.proyecto.belleza.service.CategoriaService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.base.path}/categoria")  // Path base se obtiene desde el archivo .env

public class CategoriaController {

    @Value("${api.base.path}")
    private String basePath;

   private  final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping("/agregar")
    public ResponseEntity<ResponseHttp<CategoryEntity>> agregarCategoria(@RequestBody CategoriaDto categoriaDto){

        try {
                return  ResponseHttp.successResponse("Categoria creada con éxito", this.categoriaService.createCategory(categoriaDto));
        }catch (Exception e){
            System.out.println(e);
            return  ResponseHttp.errorResponse("No fue posible crear la categoria", null);
        }
    }
    @GetMapping("/listar/{estado}")
    public ResponseEntity<ResponseHttp<List<CategoryEntity>>> listarCategorias(@PathVariable EstadoCategoria estado){

        try {
            return  ResponseHttp.successResponse("Categoria listadas con éxito", this.categoriaService.obtenerCategorias(estado));
        }catch (Exception e){
            System.out.println(e);
            return  ResponseHttp.errorResponse("No fue posible listas las categoria", null);
        }
    }
    @GetMapping("/items/listar/{estado}")
    public ResponseEntity<ResponseHttp<List<ItemEntity>>> listarItems(@PathVariable EstadoItem estado){

        try {
            return  ResponseHttp.successResponse("Items listadas con éxito", this.categoriaService.obtenerItems(estado));
        }catch (Exception e){
            System.out.println(e);
            return  ResponseHttp.errorResponse("No fue posible listas las items", null);
        }
    }
}
