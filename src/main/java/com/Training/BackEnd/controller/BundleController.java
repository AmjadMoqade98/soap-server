package com.Training.BackEnd.controller;

import com.Training.BackEnd.dao.BundleDao;
import com.Training.BackEnd.dto.BundleRequestDto;
import com.Training.BackEnd.dto.BundleResponseDto;
import com.Training.BackEnd.service.BundleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bundles")
@Scope("prototype")
public class BundleController {

    @Autowired
    BundleService bundleService;

    @GetMapping
    public List<BundleResponseDto> getBundles() {
        return bundleService.getBundles();
    }

    @GetMapping("/{id}")
    public BundleResponseDto getBundle(@PathVariable("id") int id) {
        return bundleService.getBundle(id);
    }

    @PostMapping
    public ResponseEntity addBundle(@RequestBody final BundleRequestDto bundleDto) {
        bundleService.addBundle(bundleDto);
        return new ResponseEntity<>("bundle added to the queue", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteBundle(@PathVariable("id") int id) {
        bundleService.deleteBundle(id);
        return new ResponseEntity<>("bundle added to the queue", HttpStatus.OK);
    }

//    @PostMapping("/{id}")
//    public void provision(@RequestBody final BundleDAO bundle) {
//        bundleService.addBundle(bundle);
//    }

//    @PostMapping("/soap")
//    public ResponseEntity<Object> getBundleSoap(@RequestBody final Bundle bundle) {
//        bundleService.provisionBundle(bundle);
//        return new ResponseEntity<>("bundle added to the queue", HttpStatus.OK);
//    }

    @RequestMapping(method = RequestMethod.POST, value = "/produce")
    public ResponseEntity<Object> produceBundle(@RequestBody BundleRequestDto bundleDto) {
        bundleService.produceBundle(bundleDto);
        return new ResponseEntity<>("bundle added to the queue", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/consume")
    public ResponseEntity<Object> consumeBundles() throws InterruptedException {
        bundleService.consumeBundles();
        return new ResponseEntity<>("bundles consumed successfully ", HttpStatus.OK);
    }
}
