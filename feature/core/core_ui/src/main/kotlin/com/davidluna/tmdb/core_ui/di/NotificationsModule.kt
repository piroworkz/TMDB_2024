package com.davidluna.tmdb.core_ui.di

import android.app.Application
import android.app.NotificationManager
import com.davidluna.tmdb.core_domain.entities.ChannelSpec
import com.davidluna.tmdb.core_domain.usecases.NotificationChannelSetProvider
import com.davidluna.tmdb.core_ui.R
import com.davidluna.tmdb.core_ui.model.NotificationCategories
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NotificationsModule {

    @Provides
    fun provideNotificationChannels(application: Application): NotificationChannelSetProvider =
        NotificationChannelSetProvider {
            val names: List<String> =
                application.resources.getStringArray(R.array.notification_channel_names).toList()
            val descriptions: List<String> =
                application.resources.getStringArray(R.array.notification_channel_descriptions)
                    .toList()

            NotificationCategories.entries.mapIndexed { index, category ->
                ChannelSpec(
                    id = category.name.lowercase(),
                    name = names[index],
                    importance = NotificationManager.IMPORTANCE_DEFAULT,
                    description = descriptions[index]
                )
            }
        }

}