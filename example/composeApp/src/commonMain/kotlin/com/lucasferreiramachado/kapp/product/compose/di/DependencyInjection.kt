package com.lucasferreiramachado.kapp.product.compose.di

import com.lucasferreiramachado.kapp.auth.login.domain.usecases.AuthenticateUserUseCase
import com.lucasferreiramachado.kapp.auth.login.ui.coordinator.AuthCoordinator
import com.lucasferreiramachado.kapp.auth.login.ui.coordinator.AuthCoordinatorFactoryI
import com.lucasferreiramachado.kapp.data.product.FakeProductRepository
import com.lucasferreiramachado.kapp.data.product.ProductRepository
import com.lucasferreiramachado.kapp.data.purchase.FakePurchaseRepository
import com.lucasferreiramachado.kapp.data.purchase.PurchaseRepository
import com.lucasferreiramachado.kapp.data.user.UserRepository
import com.lucasferreiramachado.kapp.data.user.model.AuthenticatedUser
import com.lucasferreiramachado.kapp.product.ProductsCoordinator
import com.lucasferreiramachado.kapp.product.ProductsCoordinatorFactoryI
import com.lucasferreiramachado.kapp.product.compose.example.ui.coordinator.ExampleCoordinator
import com.lucasferreiramachado.kapp.product.compose.example.ui.coordinator.ExampleCoordinatorFactoryI
import com.lucasferreiramachado.kapp.product.list.domain.usecases.GetProductsUseCase
import com.lucasferreiramachado.kapp.product.list.ui.coordinator.ProductListCoordinator
import com.lucasferreiramachado.kapp.product.list.ui.coordinator.ProductListCoordinatorFactoryI
import com.lucasferreiramachado.kapp.product.purchase.domain.usecases.GetCurrentPurchaseUseCase
import com.lucasferreiramachado.kapp.product.purchase.domain.usecases.SetPurchaseAddressUseCase
import com.lucasferreiramachado.kapp.product.purchase.domain.usecases.SetPurchasePaymentMethodUseCase
import com.lucasferreiramachado.kapp.product.purchase.domain.usecases.StarNewPurchaseUseCase
import com.lucasferreiramachado.kapp.product.purchase.ui.coordinator.PurchaseProductCoordinator
import com.lucasferreiramachado.kapp.product.purchase.ui.coordinator.PurchaseProductCoordinatorFactoryI
import com.lucasferreiramachado.kcoordinator.KCoordinator

class ExampleCoordinatorFactory(
    override val productCoordinatorFactory: ProductsCoordinatorFactoryI = ProductsCoordinatorFactory(),
    override val authCoordinatorFactory: AuthCoordinatorFactoryI = ExampleAuthCoordinatorFactory(),
): ExampleCoordinatorFactoryI{
    override fun create(

        parent: KCoordinator<*>,
    ): ExampleCoordinator =  ExampleCoordinator(
        factory = this,
        parent = parent
    )
}

private class ExampleAuthCoordinatorFactory(
    override val authenticateUserUseCase: AuthenticateUserUseCase = AuthenticateUserUseCase(
        repository = ExampleUserRepositoryFactory.create()
    ),
) : AuthCoordinatorFactoryI {
    override fun create(
        parent: KCoordinator<*>,
    ): AuthCoordinator = AuthCoordinator(
        this,
        parent = parent
    )
}

private object PurchaseRepositoryFactory {
    private val repository: PurchaseRepository = FakePurchaseRepository()

    fun create(): PurchaseRepository {
        return repository
    }
}

private class ProductRepositoryFactory {
    private val repository: ProductRepository = FakeProductRepository()

    fun create(): ProductRepository {
        return repository
    }
}

class ProductsCoordinatorFactory(
    override val purchaseProductCoordinatorFactory: PurchaseProductCoordinatorFactoryI = PurchaseProductCoordinatorFactory(),
    override val productListCoordinatorFactory: ProductListCoordinatorFactoryI = ProductListCoordinatorFactory(),
) : ProductsCoordinatorFactoryI {
    override fun create(
        parent: KCoordinator<*>
    ): ProductsCoordinator {
        return ProductsCoordinator(
            parent = parent,
            factory = this
        )
    }
}

private class PurchaseProductCoordinatorFactory(
    purchaseRepository: PurchaseRepository = PurchaseRepositoryFactory.create()
): PurchaseProductCoordinatorFactoryI {

    override val setPurchasePaymentMethodUseCase: SetPurchasePaymentMethodUseCase = SetPurchasePaymentMethodUseCase(purchaseRepository)
    override val setPurchaseAddressUseCase: SetPurchaseAddressUseCase = SetPurchaseAddressUseCase(purchaseRepository)
    override val getCurrentPurchaseUseCase: GetCurrentPurchaseUseCase = GetCurrentPurchaseUseCase(purchaseRepository)
    override val startNewPurchaseUseCase: StarNewPurchaseUseCase = StarNewPurchaseUseCase(purchaseRepository)

    override fun create(
        parent: KCoordinator<*>
    ): PurchaseProductCoordinator = PurchaseProductCoordinator(
        factory = this,
        parent
    )
}

private class ProductListCoordinatorFactory(
    productRepository: ProductRepository = ProductRepositoryFactory().create()
): ProductListCoordinatorFactoryI {

    override val getProductsUseCase: GetProductsUseCase = GetProductsUseCase(
        productRepository
    )

    override fun create(
        parent: KCoordinator<*>
    ): ProductListCoordinator = ProductListCoordinator(
        this,
        parent
    )
}

private object ExampleUserRepositoryFactory {
    private val repository: UserRepository = ExampleUserRepository()

    fun create(): UserRepository {
        return repository
    }
}

private class ExampleUserRepository(
    private var authenticatedUser: AuthenticatedUser? = null
): UserRepository {

    override fun authenticate(
        username: String,
        password: String,
    ): AuthenticatedUser? {
        authenticatedUser = AuthenticatedUser(
            id = "123",
            username = username,
            name = "{user's firstname}"
        )
        return authenticatedUser
    }

    override fun loggedUser(): AuthenticatedUser? = authenticatedUser
}