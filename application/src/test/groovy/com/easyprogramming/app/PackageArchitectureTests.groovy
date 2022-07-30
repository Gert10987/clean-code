package com.easyprogramming.app

import com.tngtech.archunit.core.domain.JavaClasses
import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.junit.ArchUnitRunner
import org.junit.runner.RunWith

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods

@RunWith(value = ArchUnitRunner)
@AnalyzeClasses(packages = ['scanner', 'orders', 'catalog'])
class PackageArchitectureTests {

    @ArchTest
    void domainBuildersShouldBeInvokingOnlyDuringMapping(JavaClasses classes) {
        methods().that()
                .areDeclaredInClassesThat().resideInAnyPackage("..orders..", "..catalog..")
                .and()
                .haveName("builder")
                .should()
                .onlyBeCalled().byClassesThat().haveNameMatching(".*DatabaseOutAdapterJooq")
                .check(classes)
    }
}
