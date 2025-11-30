package li.kallisto.kgnkallistoadditions.persons.leya

import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.client.particle.ParticleRenderType
import net.minecraft.client.particle.SpriteSet
import net.minecraft.client.particle.TextureSheetParticle
import net.minecraft.world.phys.Vec3

class ColorSmokeParticle(
    level: ClientLevel,
    x: Double, y: Double, z: Double,
    xSpeed: Double, ySpeed: Double, zSpeed: Double, spriteSet: SpriteSet
) : TextureSheetParticle(level, x, y, z) {
    override fun getRenderType(): ParticleRenderType =
        ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT

    init {
        lifetime = 320 + random.nextInt(100)
        pickSprite(spriteSet)
        scale(3f)
        setSize(0.25f, 0.25f)

        this.xd = xSpeed
        this.yd = ySpeed + (this.random.nextFloat() / 500.0f).toDouble()
        this.zd = zSpeed

        val colors = listOf(
            Vec3(1.0, 0.1, 0.1),
            Vec3(0.1, 1.0, 0.1),
            Vec3(0.1, 0.1, 1.0),
            Vec3(1.0, 0.6, 0.1),
            Vec3(0.8, 0.2, 1.0)
        ).random()

        setColor(colors.x.toFloat(), colors.y.toFloat(), colors.z.toFloat())
        gravity = 1.0E-2F
    }

    override fun tick() {
        this.xo = this.x
        this.yo = this.y
        this.zo = this.z
        if (this.age++ < this.lifetime && !(this.alpha <= 0.0f)) {
            this.xd += (this.random.nextFloat() / 500.0f * (if (this.random.nextBoolean()) 1 else -1).toFloat()).toDouble()
            this.zd += (this.random.nextFloat() / 500.0f * (if (this.random.nextBoolean()) 1 else -1).toFloat()).toDouble()
            this.yd -= this.gravity.toDouble()
            this.move(this.xd, this.yd, this.zd)
            if (this.age >= this.lifetime - 60 && this.alpha > 0.01f) {
                this.alpha -= 0.015f
            }
        } else {
            this.remove()
        }
    }
}