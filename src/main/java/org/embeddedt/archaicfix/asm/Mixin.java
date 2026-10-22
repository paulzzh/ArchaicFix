package org.embeddedt.archaicfix.asm;

import com.gtnewhorizon.gtnhmixins.builders.IMixins;
import com.gtnewhorizon.gtnhmixins.builders.MixinBuilder;
import cpw.mods.fml.common.Loader;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.embeddedt.archaicfix.config.ArchaicConfig;
import org.embeddedt.archaicfix.helpers.DragonAPIHelper;

import javax.annotation.Nonnull;

@Getter
@RequiredArgsConstructor
public enum Mixin implements IMixins {

    GREGTECH6(new ArchaicBuilder()
            .setPhase(Phase.LATE)
            .addRequiredMod(TargetedMod.GREGTECH6)
            .addCommonMixins(
                    "gt6.MixinAdvancedCraftingXToY",
                    "gt6.MixinGT6_Main",
                    "gt6.MixinCR")),

    MATTER_OVERDRIVE(new ArchaicBuilder()
            .setPhase(Phase.LATE)
            .addRequiredMod(TargetedMod.MATTER_OVERDRIVE)
            .addCommonMixins(
                    "mo.MixinMatterRegistry",
                    "mo.MixinMatterRegistrationHandler",
                    "mo.MixinVersionCheckHandler")),

    CHUNK_PREGENERATOR(new ArchaicBuilder()
            .setPhase(Phase.LATE)
            .addRequiredMod(TargetedMod.CHUNK_PREGENERATOR)
            .addCommonMixins(
                    "pregen.MixinChunkProcessor",
                    "pregen.MixinChunkHelper")),

    THAUMCRAFT_HASHING(new ArchaicBuilder()
            .setPhase(Phase.LATE)
            .addRequiredMod(TargetedMod.TC4TWEAKS)
            .setApplyIf(() -> ArchaicConfig.betterThaumcraftHashing)
            .addCommonMixins(
                    "tc4tweaks.MixinGenerateItemHash",
                    "tc4tweaks.MixinMappingThread")),

    MAX_RENDER_DISTANCE(new ArchaicBuilder()
            .setPhase(Phase.EARLY)
            .setApplyIf(() -> ArchaicConfig.raiseMaxRenderDistance)
            .addExcludedMod(TargetedMod.OPTIFINE)
            .addExcludedMod(TargetedMod.FASTCRAFT)
            .addCommonMixins("renderdistance.MixinPlayerManager")),

    EXTRA_UTILS(new ArchaicBuilder()
            .setPhase(Phase.LATE)
            .addRequiredMod(TargetedMod.EXTRAUTILS)
            .addCommonMixins(
                    "extrautils.MixinEventHandlerSiege",
                    "extrautils.MixinEventHandlerServer",
                    "extrautils.MixinItemDivisionSigil",
                    "extrautils.MixinTileEntityTrashCan")),

    chickenchunks_MixinPlayerChunkViewerManager(new ArchaicBuilder()
            .addCommonMixins("chickenchunks.MixinPlayerChunkViewerManager")
            .setPhase(Phase.LATE)
            .addRequiredMod(TargetedMod.CHICKENCHUNKS)),
    mrtjp_MixinBlockUpdateHandler(new ArchaicBuilder()
            .addCommonMixins("mrtjp.MixinBlockUpdateHandler")
            .setPhase(Phase.LATE)
            .addRequiredMod(TargetedMod.MRTJPCORE)),
    projecte_MixinRecipeShapelessHidden(new ArchaicBuilder()
            .addCommonMixins("projecte.MixinRecipeShapelessHidden")
            .setPhase(Phase.LATE)
            .addRequiredMod(TargetedMod.PROJECTE)),
    thermal_MixinTECraftingHandler(new ArchaicBuilder()
            .addCommonMixins("thermal.MixinTECraftingHandler")
            .setPhase(Phase.LATE)
            .addRequiredMod(TargetedMod.THERMALEXPANSION)),

    // MOD-FILTERED MIXINS
    mekanism_MixinGenHandler(new ArchaicBuilder()
            .addCommonMixins("mekanism.MixinGenHandler")
            .setPhase(Phase.LATE)
            .addRequiredMod(TargetedMod.MEKANISM)),
    thermal_MixinBlockOre(new ArchaicBuilder()
            .addCommonMixins("thermal.MixinBlockOre")
            .setPhase(Phase.LATE)
            .addRequiredMod(TargetedMod.THERMALFOUNDATION)),
    botania_MixinBlockSpecialFlower(new ArchaicBuilder()
            .addCommonMixins("botania.MixinBlockSpecialFlower")
            .setPhase(Phase.LATE)
            .addRequiredMod(TargetedMod.BOTANIA)),
    am2_MixinPlayerTracker(new ArchaicBuilder()
            .addCommonMixins("am2.MixinPlayerTracker")
            .setPhase(Phase.LATE)
            .addRequiredMod(TargetedMod.ARS_MAGICA_2)),
    foodplus_MixinUpdater(new ArchaicBuilder()
            .addCommonMixins("foodplus.MixinUpdater")
            .setPhase(Phase.LATE)
            .addRequiredMod(TargetedMod.FOODPLUS)
            .setApplyIf(() -> ArchaicConfig.disableFoodPlusUpdates)),
    waystones_MixinItemWarpStone(new ArchaicBuilder()
            .addCommonMixins("waystones.MixinItemWarpStone")
            .setPhase(Phase.LATE)
            .addRequiredMod(TargetedMod.WAYSTONES)),
    /** This mixin will ostensibly be unnecessary after DragonAPI V31b */
    dragonapi_MixinReikaWorldHelper(new ArchaicBuilder()
            .addCommonMixins("dragonapi.MixinReikaWorldHelper")
            .setPhase(Phase.LATE)
            .setApplyIf(() -> DragonAPIHelper.isVersionInInclusiveRange(0, 'a', 31, 'b') && !Boolean.parseBoolean(System.getProperty("archaicFix.disableFastReikaWorldHelper", "false")))),
    diversity_MixinServerHandler(new ArchaicBuilder()
            .addCommonMixins("diversity.MixinServerHandler")
            .setPhase(Phase.LATE)
            .addRequiredMod(TargetedMod.DIVERSITY));

    private final MixinBuilder builder;

    static class ArchaicBuilder extends MixinBuilder {
        @Override
        public MixinBuilder addCommonMixins(@Nonnull String... mixins) {
            for (int i = 0; i < mixins.length; i++) mixins[i] = "common." + mixins[i];
            return super.addCommonMixins(mixins);
        }

        @Override
        public MixinBuilder addClientMixins(@Nonnull String... mixins) {
            for (int i = 0; i < mixins.length; i++) mixins[i] = "client." + mixins[i];
            return super.addClientMixins(mixins);
        }

        @Override
        public MixinBuilder addSidedMixins(@Nonnull Side side, @Nonnull String... mixins) {
            if (side == Side.COMMON) return this.addCommonMixins(mixins);
            if (side == Side.CLIENT) return this.addClientMixins(mixins);
            return super.addSidedMixins(side, mixins);
        }
    }
}
