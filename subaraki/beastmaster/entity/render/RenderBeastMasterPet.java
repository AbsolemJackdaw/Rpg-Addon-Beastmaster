package subaraki.beastmaster.entity.render;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import subaraki.beastmaster.entity.EntityBeastmasterPet;
import subaraki.beastmaster.entity.EntityPetRooster;
import subaraki.beastmaster.entity.EntityPetSpider;
import subaraki.beastmaster.entity.model.ModelLittleHat;
import subaraki.beastmaster.entity.model.ModelPetSpider;

public class RenderBeastMasterPet<T extends EntityBeastmasterPet> extends RenderLivingBase<T> implements IRenderFactory{

	public RenderBeastMasterPet(RenderManager renderManagerIn) {
		super(renderManagerIn, null, 0.5f);
		this.addLayer(new LayerLittleHat(this));
	}

	@Override
	protected ResourceLocation getEntityTexture(T entity) {
		return entity.getPetTextures();
	}

	@Override
	public Render createRenderFor(RenderManager manager) {
		return this;
	}

	@Override
	public void doRender(T entity, double x, double y, double z, float entityYaw, float partialTicks) {
		if(this.mainModel != entity.getModel())
			this.mainModel = entity.getModel();

		super.doRender(entity, x, y, z, entityYaw, partialTicks);

	}

	@Override
	protected void preRenderCallback(T entitylivingbaseIn, float partialTickTime) {
		super.preRenderCallback(entitylivingbaseIn, partialTickTime);
		
		float scale = entitylivingbaseIn.getPetSize();
		GlStateManager.scale(scale, scale, scale);
	}

	@Override
	protected float handleRotationFloat(T livingBase, float partialTicks) {
		if(livingBase instanceof EntityPetRooster){
			EntityPetRooster rooster = (EntityPetRooster)livingBase;
			float f = rooster.oFlap + (rooster.wingRotation - rooster.oFlap) * partialTicks;
			float f1 = rooster.oFlapSpeed + (rooster.destPos - rooster.oFlapSpeed) * partialTicks;
			return (MathHelper.sin(f) + 1.0F) * f1;
		}
		return super.handleRotationFloat(livingBase, partialTicks);
	}

	public static class LayerLittleHat implements LayerRenderer<EntityLivingBase>{

		private ModelLittleHat littleHat = new ModelLittleHat();
		private ResourceLocation wool = new ResourceLocation("textures/blocks/wool_colored_black.png");
		private ResourceLocation woolred = new ResourceLocation("textures/blocks/wool_colored_red.png");
		private ResourceLocation monocle = new ResourceLocation("beastmaster:textures/pet/spider_monocle.png");

		private RenderBeastMasterPet render;
		public LayerLittleHat(RenderBeastMasterPet render) {
			this.render = render;
		}

		@Override
		public void doRenderLayer(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount,
				float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {

			if(entitylivingbaseIn instanceof EntityPetSpider && entitylivingbaseIn.getCustomNameTag() != null && entitylivingbaseIn.getCustomNameTag().equals("Jack")){
				if(render.mainModel instanceof ModelPetSpider){
					renderSpiderHat();
				}
			}
		}

		@Override
		public boolean shouldCombineTextures() {
			return false;
		}

		private void renderSpiderHat(){
			GlStateManager.pushMatrix();

			((ModelPetSpider)render.mainModel).head.postRender(0.0625f);

			render.bindTexture(monocle);
			GlStateManager.translate(0.15, -0.83, 0.18);
			new ModelPetSpider().head.render(0.0635f);
			GlStateManager.translate(-0.15, 0.83, -0.18);

			GlStateManager.translate(-0.18, -0.2, -0.15);

			GlStateManager.rotate(180, 1, 0, 0);
			GlStateManager.rotate(45, 0, -1, 1);

			render.bindTexture(wool);
			littleHat.render(0.0625f);

			render.bindTexture(woolred);
			littleHat.renderString(0.0625f);

			GlStateManager.popMatrix();
		}
	}
}
